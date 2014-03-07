/*
 * Employee.cpp
 *
 *  Created on: Mar 7, 2014
 *      Author: douglas
 */

#include "Employee.h"

Employee::Employee(string fname, string lname, int s) {
	firstName = fname;
	lastName = lname;
	salary = s;
}
Employee::Employee(const Employee& obj) {
	if (obj.firstName == "") {
		firstName = obj.firstName;
	} else
		firstName = "";
	if (obj.lastName == "") {
		lastName = obj.lastName;
	} else
		lastName = "";
	if (salary > 0) {
		salary = obj.salary;
	} else {
		salary = 0;
	}
}
const string Employee::getfirstName() const {
	return firstName;
}
const string Employee::getlastName() const {
	return lastName;
}
const int Employee::getsalary() const {
	return salary;
}
Employee::~Employee() {
}
void Employee::display() {
	cout << "first name is" << getfirstName() << "last name is" << getlastName()
			<< "total salary is " << getsalary() << endl;
}
;
void Employee::setfirstName(string f) {
	firstName = f;
}

void Employee::setlastName(string l) {
	lastName = l;
}

void Employee::setsalary(int sal) {
	salary = sal;
}
