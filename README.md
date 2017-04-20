# flyway-mixed-spike

Demonstrates using Java and pure SQL to drive migrations in [Flyway](https://flywaydb.org/).

Try the [SQL-only](https://github.com/ajwhatling-wk/flyway-spike) and [Java-only](https://github.com/ajwhatling-wk/flyway-java-spike) migrations first.  The same principles from both apply here minus the test data, though adding in test data for mixed migrations shouldn't be difficult at all.

Major disadvantage of mixed mode: The prefix-- `VX_X_X`-- need to be synchronized between both the SQL files and the Java files.  If `V1_0_0__foo.sql` and `V1_0_1__bar.sql` are defined, then Java migrations must be something like `V1_0_2__baz.java` or `V2_0_0__quux.java`.
