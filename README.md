# DSA (Data Structures and Algorithms) Progress in Java

## Overview

This repository contains my solutions and progress tracking for LeetCode problems using Java. Each problem is solved using various data structures and algorithms to improve understanding and proficiency in programming.

## Structure

The repository is organized by topics and difficulty levels. Each problem solution includes:

- **Problem Statement**: Brief description of the problem.
- **Solution**: Java code implementation.
- **Complexity Analysis**: Time and space complexity analysis of the solution.
- **Alternative Approaches**: Other possible solutions or optimizations.

## Example

### 207. Course Schedule

```java
// 207. Course Schedule
public boolean canFinish(int numCourses, int[][] prerequisites)
{
    Stack<Integer> stack = new Stack<>();
    List<List<Integer>> list = new ArrayList<>();
    boolean visited[] = new boolean[numCourses];

    for (int i = 0; i < numCourses; i++)
        list.add(new ArrayList<>());

    for (int e[] : prerequisites)
        list.get(e[0]).add(e[1]);

    if (this.isCycle(list))
        return false;

    for (int i = 0; i < numCourses; i++)
    {
        if (!visited[i])
            this.canFinishHelper(list, i, stack, visited);
    }

    return stack.size() == numCourses;
}

private void canFinishHelper(List<List<Integer>> list, int course, Stack<Integer> stack, boolean[] visited)
{
    visited[course] = true;
    for (int i : list.get(course))
    {
        if (!visited[i])
            this.canFinishHelper(list, i, stack, visited);
    }
    stack.push(course);
}
