# cis2232_f26_project_dasilva_jesse

VEHICLE RENTAL TRACKER

PROJECT GROUP
--------------------------------
BA/ BUSINESS CLIENT   | Thomas

DEVELOPER             | Jesse

PROJECT MANAGER / QA  | Cameron



PROJECT BASE COLOUR
-------------------
Red #b6042a

DESCRIPTION:
------------
This project will track the details of a vehicle rental sale. It will include the colour and type of vehicle(car, van, truck, or electric), the number of days it is rented for, and the name and address of the customer.  The project will also calculate the cost of the sale based on the vehicle type and the time rented. The project should also include the date of the sale.

FIELDS
-------

saleID          |  int         |  ID used to differentiate sales

vehicleType     |  String      |  Type of vehicle (Car, Van, Truck, Electric)

vehicleColour   |  String      |  Colour of vehicle

rentalTimeDays  |  int         |  Number of days rented for

custFName       |  String      |  Customer first name

custLName       |  String      |  Customer last name

custAddress     |  String      |  Customer address

saleTotal       |  double      |  Total cost of rental

rentDate        |  String      |  Date of rental sale        

CONSTANTS
---------

int    CAR_PRIRCE     = 35 

int    VAN_PRICE      = 49

int    TRUCK_PRICE    = 40

int    ELECTRIC_PRICE = 45

double TAX_RATE       = 1.15


CALCULATION
-----------
saleTotal will be calculated by the cost of vehicle based on the type, multiplied by the length of days rented, then multipled by the tax rate.

I added a variable, costOfVehicle, to store the vehicle cost based on the type.

The calculation goes as followed:

> saleTotal = (costOfVehicle * rentalTimeDay) * TAX_RATE

## Reports

Reports are available from the **Reports** menu in the navigation bar, or at `/report`.

### Rentals by Customer Name
**URL:** `/report/tracker/name`

Shows every rental made by a customer.

- **Input:** customer first name and last name
- **Output:** a table with sale ID, rental date, customer name, address, vehicle type, vehicle colour, rental length in days and sale total
- **No matches:** a message says no records were found under that name
- **File output:** each time the report runs, the results are also written to a file named `customerNameReport`
- **How it works:** `ReportController` takes the form input and calls `VehicleRentalTrackerBO`, which asks `VehicleRentalTrackerDAO` for the matching rows. The DAO uses a prepared statement on the `custFName` and `custLName` columns.
