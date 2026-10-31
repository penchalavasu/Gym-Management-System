Fitness Care - membership history feature (final source)

New database: run database/schema.sql, then optionally seed_data.sql. WARNING: schema.sql drops existing tables.
Existing database without membership history: run database/add_membership_history.sql (non-destructive).
Existing database with the prior membership-history patch: run database/protect_existing_membership_history.sql to protect historical records.

Membership records are retained across renewals. To prevent loss of history, deletion of a member who has membership history is blocked by the database. The existing member-delete UI may display an error for such a member; other functionality is unchanged.

Build using: mvn clean package (JDK 21, Maven 3.9+, MySQL 8, Tomcat 10.1).
This archive has not been end-to-end tested against a running MySQL/Tomcat instance in this environment.
