
Hibernate

This is an object relational mapping technology that is supposed to simplify development so that you don't have to wrote so much code.

JPA

This is the specification that defines how an object relational mapping technology should function in java.  What happens is a group of people come together and define a specification that lists all the requirements that an Object Relational Mapping technology must provide to be compliant with the specification.  Then after the specification is published people have the opportunity to implement code that follows the specification.  Hibernate implemented that specification and be what is called a JPA provider.  Essentially that means that it provides an implementation of the specification.  So it's possible to use Hibernate without using JPA.  The Hibernate example in the codebase is an example of that.  However, by creating the JPA specification it becomes possible to switch between different provider without having to change code.  For example, TopLink is another technology that implmented the JPA specification.  Thus, its completely, possible to use the Hibernate JPA provider and then switch to the TopLInk provider and your SHOULD still work.

JDBC

This is the lowest level of API in Java that you can work with when talking to a database.  The examples in the codebase show the type of code you must write when working directly with this API.  It should be clear that you write more code.  However, you also have MORE control over the code since nothing is getting in your way.  This is a tradeoff.