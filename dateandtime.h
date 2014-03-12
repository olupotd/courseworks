/*
 * datetime.h
 *
 *  Created on: Mar 12, 2014
 *      Author: douglas
 */

#ifndef DATETIME_H_
#define DATETIME_H_
#include<iostream>
#include<iomanip>
using namespace std;
class dateandtime {
//by default all members in a class are private so it's better to start with public rather than private
public:
	int second;
	bool leapyear();
	int monthdays();
	dateandtime(int = 1, int = 1, int = 1900, int = 0, int = 0, int = 0);
	void setdate(int, int, int);
	void setMonth(int);
	void setDay(int);
	void setYear(int);
	void nextday();
	void settime(int, int, int);
	void setHour(int);
	void setMinute(int);
	void setSecond(int);
	void tick();
	int getmonth();
	int getday();
	int getyear();
	int getyour();
	int getminute();
	int getsecond();
	int gethour();
	void printstandard();
	void printuniversal();
	virtual ~dateandtime();
private:
	int month;
	int day;
	int year;
	int hour;
	int minute;
	int Day;
};

#endif /* DATETIME_H_ */

