/*
 * Employee.h
 *
 *  Created on: Mar 7, 2014
 *      Author: douglas
 */

#ifndef EMPLOYEE_H_
#define EMPLOYEE_H_

#include <iostream>
#include <string.h>

using namespace std;

class Employee {

public:
	Employee(){};
	Employee(string,string, int);
	Employee(const Employee& obj);
	void display();
	void setfirstName(string);
	void setlastName(string);
	void setsalary(int sal);
	const string getfirstName() const;
	const string getlastName() const;
	const int getsalary() const;
	~Employee();
private:
	string firstName;
	string lastName;
	int salary;
};
#endif
