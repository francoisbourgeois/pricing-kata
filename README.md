# pricing-kata

This project is a solution for a coding kata

# Basic idea of the solution
- Pricing rules are the center of the calculation
- Rules are executed in order and are thereby priorized
- Every rule calculates a partial sum and marks all items it "used"
- The marked items are removed from the (temporary) item set and will be omitted by all following rules
- Repetition of rules has to be implemted in the rules themselves

# How to run
There is no main entry point into the project but only unit tests.

## Prerequisites
- Java 8
- Gradle

## Run the tests
	gradle clean test
