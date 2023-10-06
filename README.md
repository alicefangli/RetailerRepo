# RetailerRepo
A Retailer Customer Points Application

This spring boot microservice used spring MVC architecture to process a retailer reward to customers

Currently datasource is not set yet.
relational database has two tables as following:

Customer_table				
customer_id	fullName	address	                phone	    email
1	        John Smith	123 Main St, Palo, CA	123456789	john.smith@example.com
2	        David Hall	234 Adam Ave, Bell, CA	234567891	david.hall@example.com
3	        Aaron Sade	345 Bell St, Carlo, CA	345678912	aaron.sade@example.com
4	        Bob Adams	456 Charlie St, Dell, CA	456789123	bob.adams@example.com

Transaction_table				
transaction_id	customer_id	purchase_date	purchase_amount	points
1	            1	        08/02/23	    48.45	        0
2	            1	        08/08/23	    59.31	        9
3	            1	        09/15/23	    111.5	        72
4	            1	        09/24/23	    68.7	        18
5	            2	        07/05/23	    98.78	        48
6	            2	        08/03/23	    68.75	        18
7	            2	        08/14/23	    45.32	        0
8	            2	        09/30/23	    125.86	        100
9	            3	        08/01/23	    76.53	        26
10	            3	        09/05/23	    84.31	        34
11	            3	        09/14/23	    32.15	        0
12	            3	        10/02/23	    65.89	        15
13	            4	        08/09/23	    76.35	        26
14	            4	        09/16/23	    123.45	        96
15	            4	        10/01/23	    45.87	        0

Logging: slf4j is for logging. Currently has some setting problem

Health check: spring actuator is used for health check with curl command.