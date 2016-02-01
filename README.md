# SHOE STORES

##### Epicodus friday's code review project using Java and Postgres, 01.29.2016

##### Illia Kuchko

## Description
Shoe stores application, created to collect and manage data about shoe brands and stores that selling them.

## Setup

Clone this repository:
```
$ cd ~/Desktop
$ git clone https://github.com/ikuchko/shoe_stores.git
$ cd shoe_stores
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and create the `hair_salon` database:
```
$ psql
$ CREATE DATABASE shoe_stores;
$ psql shoe_stores < shoe_stores.sql
```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

##Database information
In PSQL:
```
CREATE DATABASE shoe_stores;
CREATE TABLE stores (id serial PRIMARY KEY, name varchar, address varchar, phone_number varchar);
CREATE TABLE brands (id serial PRIMARY KEY, name varchar);
CREATE TABLE stores_brands (id serial PRIMARY KEY, int store_id REFERENCES stores (id), int brand_id REFERENCES brands (id));
```

## Legal

Copyright (c) 2015 Illia Kuchko

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
