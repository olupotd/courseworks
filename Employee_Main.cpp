/*
 * Employee_Main.cpp
 *
 *  Created on: Mar 7, 2014
 *      Author: douglas
 */
#include <iostream>
#include "Employee.h"

int main() {
	string Title("MRS-"),name ("Sarah"),first ("Super"), last = "Man";
	Employee e(Title, name, 8000);

	cout << "First Employee FullName is: " << e.getfirstName()
			<< e.getlastName() << endl;
	cout << "First Employee Salary is :" << e.getsalary() << endl;

	Employee obj;
	obj.setfirstName(first);
	obj.setlastName(last);
	obj.setsalary(150);
	obj.display(); //cout << "2nd Employee FullName is: " << obj.getfirstName() << obj.getlastName() << endl; //cout << "2nd Employee Salary is :" << obj.getsalary() << endl; system("pause"); return 0;

}
