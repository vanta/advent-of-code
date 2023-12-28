package pl.vanta.adventofcode.year2023;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pl.vanta.adventofcode.ParserSolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Day20 implements ParserSolver<List<Day20.Module>, Long> {

    private static final String BROADCASTER = "broadcaster";

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
        parsedInput.forEach(System.out::println);

        var map = parsedInput.stream()
                .collect(toMap(
                        Module::getName,
                        m -> m
                ));

        parsedInput.stream()
                .filter(m -> m instanceof Conjunction)
                .map(Conjunction.class::cast)
                .forEach(m -> m.setInputs(findInputs(map, m.getName())));


        for (int i = 0; i < 1000; i++) {
            process(map, List.of(new Pulse(false, null, BROADCASTER)));
        }

        var pair = parsedInput.stream()
                .map(m -> new ImmutablePair<>(m.getLowPulses(), m.getHighPulses()))
                .reduce(new ImmutablePair<>(0, 0), (p1, p2) -> new ImmutablePair<>(p1.getLeft() + p2.getLeft(), p1.getRight() + p2.getRight()));

        return (long) pair.left * pair.right;
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

    @Override
    public Long solve2(List<Module> parsedInput) {
        return 0L;
    }

    private record Pulse(boolean value, String from, String to) {
    }

    abstract public static class Module {
        private final String name;
        private final List<String> outputs;
        private final Map<Boolean, Integer> pulses = new HashMap<>();

        abstract Boolean processPulse(String from, boolean pulse);

        Module(String name, String... outputs) {
            this.name = name;
            this.outputs = Arrays.asList(outputs);
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

    class Broadcaster extends Module {
        Broadcaster(String... outputs) {
            super(BROADCASTER, outputs);
        }

        @Override
        Boolean processPulse(String from, boolean pulse) {
            return pulse;
        }
    }

    class FlipFlop extends Module {
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

    class Conjunction extends Module {
        private final Map<String, Boolean> pulses = new HashMap<>();

        Conjunction(String name, String... outputs) {
            super(name, outputs);
        }

        void setInputs(Set<String> inputs) {
            inputs.forEach(s -> pulses.put(s, false));
        }

        @Override
        Boolean processPulse(String from, boolean pulse) {
            pulses.put(from, pulse);

            return pulses.containsValue(false);
        }
    }
}
