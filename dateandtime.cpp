/*
 * datetime.cpp
 *
 *  Created on: Mar 12, 2014
 *      Author: douglas
 */

#include "dateandtime.h"

dateandtime::dateandtime(int m, int d, int y, int hr, int min, int sec) {
	setdate(m, d, y);
	settime(hr, min, sec);
}
void dateandtime::setdate(int mo, int dy, int yr) {
	setMonth(mo);
	setDay(dy);
	setYear(yr);
}
void dateandtime::setDay(int d) {
	if (month == 2 && leapyear())
		day = (d <= 29 && d >= 1) ? d : 1;
	else
		day = (d <= monthdays() && d >= 1) ? d : 1;
}
void dateandtime::setMonth(int m) {
	month = m <= 12 && m >= 1 ? m : 1;
}
void dateandtime::setYear(int y) {
	year = y >= 1900 ? y : 1900;
}
void dateandtime::nextday() {
	setDay(day + 1);
	if (day == 1) {
		setMonth(month + 1);
		if (month == 1)
			setYear(year + 1);
	}
}
void dateandtime::settime(int hr, int min, int sec) {
	setHour(hr);
	setMinute(min);
	setSecond(sec);
}
void dateandtime::setHour(int h) {
	hour = (h >= 0 && h < 24) ? h : 0;
}
void dateandtime::setMinute(int m) {
	minute = (m >= 0 && m < 60) ? m : 0;
}
void dateandtime::setSecond(int s) {
	second = (s >= 0 && s < 60) ? s : 0;
}
void dateandtime::tick() {
	setSecond(second + 1);
	if (second == 0) {
		setMinute(minute + 1);
		if (minute == 0) {
			setHour(hour + 1);
			if (hour == 0)
				nextday();
		}
	}
}
int dateandtime::getday() {
	return Day;
}
int dateandtime::getmonth() {
	return month;
}
int dateandtime::getyear() {
	return year;
}
int dateandtime::gethour() {
	return hour;
}
int dateandtime::getminute() {
	return minute;
}
int dateandtime::getsecond() {
	return second;
}
void dateandtime::printstandard() {
	cout << ((hour % 12 == 0) ? 12 : hour % 12) << ':'
			<< (minute < 10 ? "0" : "") << minute <<
	':' << (second < 10 ? "0" : "") << second << (hour < 12 ? "AM" : "PM")
			<< month << '-' << day << '-' << year << endl;
}
void dateandtime::printuniversal() {
	cout << (hour < 10 ? "0" : "") << hour <<
	':' << (minute < 10 ? "0" : "") << minute <<
	':' << (second < 10 ? "0" : "") << second <<
	" " << month <<
	'-' << day <<
	'-' << year << endl;
}
bool dateandtime::leapyear() {
	if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
		return true;
	else
		return false;
}
int dateandtime::monthdays() {
	const int days[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	return (month == 2 && leapyear()) ? 29 : days[(month - 1)];
}

dateandtime::~dateandtime() {
// TODO Auto-generated destructor stub
}

int main() {
const int MAXTICKS = 30;
dateandtime d(12, 31, 2004, 23, 59, 57);
for (int tick = 1; tick <= MAXTICKS; ++tick) {
	cout << "universal time";
	d.printuniversal();
	cout << "standard time";
	d.printstandard();
	d.tick();
}
return 0;
}

