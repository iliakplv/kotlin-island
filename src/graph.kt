import java.util.*


fun main(args: Array<String>) {
    val graph: MutableMap<String, MutableSet<String>> = HashMap();

    addEdge(graph, "a", "b")
    addEdge(graph, "b", "c")
    addEdge(graph, "c", "d")
    addEdge(graph, "x", "a")
    addEdge(graph, "x", "y")

    println("\nCycle found: ${containsCycle(graph)}")
}

private fun addEdge(graph: MutableMap<String, MutableSet<String>>,
                    fromVertex: String,
                    toVertex: String) {

    val adjacentVertices: MutableSet<String>
    if (graph.containsKey(fromVertex)) {
        adjacentVertices = graph[fromVertex] ?: HashSet();
    } else {
        adjacentVertices = HashSet()
        graph.put(fromVertex, adjacentVertices)
    }

    adjacentVertices.add(toVertex)

    if (!graph.containsKey(toVertex)) {
        graph.put(toVertex, HashSet())
    }
}

private fun containsCycle(graph: MutableMap<String, MutableSet<String>>): Boolean {
    val visitedVertices = HashSet<String>()

    for (vertex in graph.keys) {
        println("Depth search from: $vertex")
        if (depthSearchForCycle(graph, visitedVertices, HashSet(), vertex)) {
            return true
        }
    }

    return false
}

private fun depthSearchForCycle(graph: MutableMap<String, MutableSet<String>>,
                                visitedVertices: MutableSet<String>,
                                reachableVertices: MutableSet<String>,
                                currentVertex: String): Boolean {

    // search in unvisited vertices
    if (!visitedVertices.contains(currentVertex)) {

        println(currentVertex)

        // if marked as reachable from current search then cycle found
        if (reachableVertices.contains(currentVertex)) {
            return true
        }

        // mark as reachable from current search
        reachableVertices.add(currentVertex)

        // continue search in vertices adjacent to current
        val adjacentVertices = graph[currentVertex]
        if (adjacentVertices != null) {
            for (adjacentVertex in adjacentVertices) {
                if (depthSearchForCycle(graph, visitedVertices, reachableVertices, adjacentVertex)) {
                    return true
                }
            }
        }

        // mark as visited
        visitedVertices.add(currentVertex)
    }

    return false
}