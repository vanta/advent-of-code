package pl.vanta.adventofcode.year2023;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pl.vanta.adventofcode.ParserSolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Day20 implements ParserSolver<List<Day20.Module>, Long> {

    private static final String BROADCASTER = "broadcaster";
    private static final String RX = "rx";

    @Override
    public int getDayNumber() {
        return 20;
    }

    @Override
    public List<Module> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(this::parseModule)
                .toList();
    }

    private Module parseModule(String s) {
        var split = s.split(" -> ");
        var outputs = split[1].split(", ");

        if (BROADCASTER.equals(split[0])) {
            return new Broadcaster(outputs);
        }
        if (split[0].charAt(0) == '%') {
            return new FlipFlop(split[0].substring(1), outputs);
        }
        if (split[0].charAt(0) == '&') {
            return new Conjunction(split[0].substring(1), outputs);
        }

        throw new IllegalArgumentException("Unknown module: " + s);
    }

    @Override
    public Long solve(List<Module> parsedInput) {
        var map = preProcess(parsedInput);

        //push button 1000 times
        for (int i = 0; i < 1000; i++) {
            process(map, List.of(new Pulse(false, null, BROADCASTER)));
        }

        //count
        var pair = map.values().stream()
                .map(m -> new ImmutablePair<>(m.getLowPulses(), m.getHighPulses()))
                .reduce(new ImmutablePair<>(0, 0), (p1, p2) -> new ImmutablePair<>(p1.getLeft() + p2.getLeft(), p1.getRight() + p2.getRight()));

        return (long) pair.left * pair.right;
    }

    @Override
    public Long solve2(List<Module> parsedInput) {
        var map = preProcess(parsedInput);

        long i = 0;
        var bc = map.get(BROADCASTER);
        var rx = map.get(RX);

        System.out.println(bc);
        System.out.println(rx);
        rx.getInputs().stream()
                .map(map::get)
                .forEach(System.out::println);

//        while (!(rx.getHighPulses() == 0 && rx.getLowPulses() == 1)) {
//            i++;
//            process(map, List.of(new Pulse(false, null, BROADCASTER)));
//        }

        return i;
    }

    private Map<String, Module> preProcess(List<Module> parsedInput) {
        var map = parsedInput.stream()
                .collect(toMap(
                        Module::getName,
                        identity()
                ));

        //add untyped modules
        var missingModules = parsedInput.stream()
                .map(Module::getOutputs)
                .flatMap(List::stream)
                .filter(s -> !map.containsKey(s))
                .collect(toSet());

        missingModules.forEach(m -> map.put(m, new Untyped(m)));

        //set inputs
        map.values().forEach(m -> m.setInputs(findInputs(map, m.getName())));

        return map;
    }

    private Set<String> findInputs(Map<String, Module> map, String moduleName) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().getOutputs().contains(moduleName))
                .map(Map.Entry::getKey)
                .collect(toSet());
    }

    private void process(Map<String, Module> modules, List<Pulse> pulses) {
        if (pulses.isEmpty()) {
            return;
        }

        var newPulses = pulses.stream()
                .map(pulse -> processPulse(modules, pulse))
                .flatMap(List::stream)
                .toList();

        process(modules, newPulses);
    }

    private List<Pulse> processPulse(Map<String, Module> modules, Pulse pulse) {
        var module = modules.get(pulse.to);
        var result = module.pulse(pulse.from, pulse.value);

        if (result == null) {
            return List.of();
        } else {
            return module.getOutputs().stream()
                    .map(o -> new Pulse(result, module.name, o))
                    .toList();
        }
    }

    private record Pulse(boolean value, String from, String to) {
    }

    abstract public static class Module {
        private final String name;
        private final List<String> outputs;
        private final Map<Boolean, Integer> pulses = new HashMap<>();
        Set<String> inputs;

        abstract Boolean processPulse(String from, boolean pulse);

        public String toString() {
            return "%s (%s), in: %s, out: %s".formatted(name, this.getClass().getSimpleName(), inputs, outputs);
        }

        Module(String name, String... outputs) {
            this.name = name;
            this.outputs = Arrays.asList(outputs);
        }

        void setInputs(Set<String> inputs) {
            this.inputs = inputs;
        }

        public Set<String> getInputs() {
            return inputs;
        }

        public Boolean pulse(String from, boolean pulse) {
            pulses.merge(pulse, 1, Integer::sum);

            return processPulse(from, pulse);
        }

        public String getName() {
            return name;
        }

        public List<String> getOutputs() {
            return outputs;
        }

        public int getLowPulses() {
            return pulses.getOrDefault(false, 0);
        }

        public int getHighPulses() {
            return pulses.getOrDefault(true, 0);
        }
    }

    static class Broadcaster extends Module {
        Broadcaster(String... outputs) {
            super(BROADCASTER, outputs);
        }

        @Override
        Boolean processPulse(String from, boolean pulse) {
            return pulse;
        }
    }

    static class FlipFlop extends Module {
        boolean enabled = false;

        FlipFlop(String name, String... outputs) {
            super(name, outputs);
        }

        @Override
        Boolean processPulse(String from, boolean pulse) {
            if (pulse) {
                return null;
            } else {
                enabled = !enabled;

                return enabled;
            }
        }
    }

    static class Conjunction extends Module {
        private final Map<String, Boolean> pulses = new HashMap<>();

        Conjunction(String name, String... outputs) {
            super(name, outputs);
        }

        @Override
        Boolean processPulse(String from, boolean pulse) {
            pulses.put(from, pulse);

            return !pulses.keySet().containsAll(inputs) || pulses.containsValue(false);
        }
    }

    static class Untyped extends Module {
        Untyped(String name) {
            super(name);
        }

        @Override
        Boolean processPulse(String from, boolean pulse) {
            return null;
        }
    }
}
