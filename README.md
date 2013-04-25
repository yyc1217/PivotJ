Hierarchical-Statistic-Tree (HST)
================================
This is a data structure which likes the pivot table in Excel. It could be used to hierarchically accumulate value in Java, Especially **'Group By'** operations.

Why?
--------------------------------
In many object-oriented languages, some times it is hard to do **'Group By'** like operations to get the value we want just once. We may use a lot of map structures or loops to calculate different values in different layers, which is annoying. 

For instance, **Hibernate**, a Java persistence framework for relational mapping and query databases. We are used to manipulating data in tables likes manipulate objects in Java.

But for **'Group By'** operations, we could execute them both in Java and in databases by query(SQL, HQL...etc). In Java implementation, it's very complicate and difficult because of its object-oriented feature.

How
-----------------------------
You may query data like this:

    SELECT a.company, a.department, a.team, COUNT(*) FROM
    Employee a 
    Group By a.company, a.department, a.team

and in Java:

    ....
    Query qry = EntityManager.createQuery(queryString);
    ....	
    return qry.getResultList();

*query.getResultList()* will return a list of Object Arrays:

    [...
    Object[]{"CompanyA", "DepartmentA", "TeamA", 3},
    Object[]{"CompanyA", "DepartmentA", "TeamB", 2},
    Object[]{"CompanyB", "DepartmentB", "TeamB", 1},
    ...]

you might use these result as key to build a Hierarchical Statistic Tree:

    IHierarchicalStatisticTree HST = new SumTree();
    HST.buildTree(list);

then you cound get the accumulated value by calling:

    HST.getResult(new Object[]{"CompanyB", "DepartmentB"});
    //1
    HST.getResult(new Object[]{"CompanyA"});
    //5

The difference between SumTree.java and CountTree.java is that former treats the last element in object array as calculating value, but latter just count every element.

Examples
-----------------------------
* See SumTreeDemo.java, CountTreeDemo.java.
