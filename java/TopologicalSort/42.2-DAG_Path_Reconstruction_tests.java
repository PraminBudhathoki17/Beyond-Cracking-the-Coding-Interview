import java.util.*;

record TestCase(List<List<Edge>> graph, int start, int goal, List<Integer> want) {}

class RunTests {
    public void runTests() {
        TestCase[] tests = {
                new TestCase(
                        Arrays.asList(
                                Arrays.asList(new Edge(1, 10)),
                                new ArrayList<>(),
                                Arrays.asList(new Edge(1, 10)),
                                Arrays.asList(new Edge(4, 12)),
                                Arrays.asList(new Edge(1, 11), new Edge(2, 21), new Edge(5, 14)),
                                Arrays.asList(new Edge(2, -30))),
                        4, 1, Arrays.asList(4, 5, 2, 1)),

                new TestCase(
                        Arrays.asList(new ArrayList<>()),
                        0, 0, Arrays.asList(0)),

                new TestCase(
                        Arrays.asList(
                                Arrays.asList(new Edge(1, 5)),
                                new ArrayList<>(),
                                Arrays.asList(new Edge(3, 2)),
                                new ArrayList<>()),
                        0, 3, Arrays.asList()),

                new TestCase(
                        Arrays.asList(
                                Arrays.asList(new Edge(1, -1)),
                                Arrays.asList(new Edge(2, -2)),
                                new ArrayList<>()),
                        0, 2, Arrays.asList(0, 1, 2)),

                new TestCase(
                        Arrays.asList(
                                Arrays.asList(new Edge(1, 2)),
                                Arrays.asList(new Edge(2, 3)),
                                new ArrayList<>()),
                        2, 0, Arrays.asList())
        };

        ShortestPath solution = new ShortestPath();

        for (TestCase test : tests) {
            List<Integer> got = solution.solve(test.graph(), test.start(), test.goal());
            if (!got.equals(test.want())) {
                throw new RuntimeException(String.format(
                        "\nsolve(graph, %d, %d):\ngot:  %s\nwant: %s\n",
                        test.start(), test.goal(), got, test.want()));
            }
        }
    }
}
