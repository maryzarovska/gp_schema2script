CREATE TABLE student (
    student_id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES course (course_id)
);

CREATE TABLE instructor (
    instructor_id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    hire_date DATE,
    FOREIGN KEY (instructor_id) REFERENCES course (instructor_id)
);

CREATE TABLE course (
    course_id INT,
    course_name VARCHAR(100),
    credits INT,
    instructor_id INT,
    FOREIGN KEY (course_id) REFERENCES student (student_id),
    FOREIGN KEY (instructor_id) REFERENCES instructor (instructor_id)
);

CREATE TABLE enrollment (
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (course_id) REFERENCES course (course_id)
);

