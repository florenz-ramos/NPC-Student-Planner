package com.npc.dev.app;

public class SQLiteParams
{
	
	public static final String dbName="Student.db";
	public static final String[] tables={"tbldummy","tblsetup",
		"tblprofile","tblprof","tblsubjects","tblnotes","tblevents","tblpnote"};
	public static final String[] tbl1cols={"ID","VALUE"};
	public static String[] Queries={
		"CREATE TABLE "+tables[0]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,VALUE TEXT)",
		"CREATE TABLE "+tables[1]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,VALUE TEXT)",
		"CREATE TABLE "+tables[2]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUDENT_ID TEXT,FIRSTNAME TEXT,LASTNAME TEXT,COURSE TEXT,PICTURE TEXT)",
		"CREATE TABLE "+tables[3]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FNAME TEXT,LNAME TEXT)",
		"CREATE TABLE "+tables[4]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,SUBCODE TEXT,SUBNAME TEXT,UNITS INTEGER,ROOM TEXT,PROFESSOR TEXT,TIMEFROM TEXT,TIMETO TEXT,DAY TEXT)",
		"CREATE TABLE "+tables[5]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOTETITLE TEXT,NOTECONTENT TEXT,NOTEDATE TEXT)",
		"CREATE TABLE "+tables[6]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EVENTTITLE TEXT,EVENTSTART TEXT,EVENTEND TEXT,EVENTDESC TEXT)",
   	"CREATE TABLE "+tables[7]+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOTETITLE TEXT,NOTECONTENT TEXT,NOTEDATE TEXT)"
		
		
	};
	public static String[] SelectQueries={
		"Select NOTETITLE from tblNotes ORDER BY ID DESC",
		"Select NOTECONTENT from tblNotes ORDER BY ID DESC",
		"Select NOTEDATE from tblNotes ORDER BY ID DESC",
		"Select SUBCODE from tblsubjects",
		"Select SUBNAME from tblsubjects",
		"Select ROOM from tblsubjects",
		"Select TIMEFROM,TIMETO from tblsubjects",
		"Select DAY from tblsubjects",
		"Select PROFESSOR from tblsubjects"
	};



}
