import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

record TestCase(List<List<Edge>> graph, int start, int[] want) {
}

class RunTests {
  public void runTests() {
    TestCase[] tests = {
        // Example from the book
        new TestCase(
            Arrays.asList(
                Arrays.asList(new Edge(1, 10)),
                new ArrayList<>(),
                Arrays.asList(new Edge(1, 10)),
                Arrays.asList(new Edge(4, 12)),
                Arrays.asList(new Edge(1, 11), new Edge(2, 21),
                    new Edge(5, 14)),
                Arrays.asList(new Edge(2, -30))),
            4,
            new int[] { Integer.MIN_VALUE, 31, 21, Integer.MIN_VALUE, 0, 14 }),

        // Edge case: Single node graph
        new TestCase(
            Arrays.asList(new ArrayList<>()),
            0,
            new int[] { 0 }),

        // Edge case: Disconnected graph
        new TestCase(
            Arrays.asList(
                Arrays.asList(new Edge(1, 5)),
                new ArrayList<>(),
                Arrays.asList(new Edge(3, 2)),
                new ArrayList<>()),
            0,
            new int[] { 0, 5, Integer.MIN_VALUE, Integer.MIN_VALUE }),

        // Edge case: Graph with negative weights
        new TestCase(
            Arrays.asList(
                Arrays.asList(new Edge(1, -1)),
                Arrays.asList(new Edge(2, -2)),
                new ArrayList<>()),
            0,
            new int[] { 0, -1, -3 }),

        // Edge case: Start node with no outgoing edges
        new TestCase(
            Arrays.asList(
                Arrays.asList(new Edge(1, 2)),
                Arrays.asList(new Edge(2, 3)),
                new ArrayList<>()),
            2,
            new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE, 0 })
    };

    LongestPath solution = new LongestPath();
    for (TestCase test : tests) {
      int[] got = solution.solve(test.graph(), test.start());
      if (!Arrays.equals(got, test.want())) {
        throw new RuntimeException(String.format(
            "\nsolve(%s, %d): got: %s, want: %s\n",
            test.graph(), test.start(), Arrays.toString(got),
            Arrays.toString(test.want())));
      }
    }
  }
}
