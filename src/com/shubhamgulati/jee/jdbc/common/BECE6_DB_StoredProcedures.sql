Stored Procedures:

1. Stored Procedure 1

delimiter //

CREATE PROCEDURE getAllStudents()
BEGIN
	SELECT * FROM students_info;
END //

delimiter ;



2. Stored Procedure 2

delimiter //

CREATE PROCEDURE getStudentInfo(IN in_reg_no INT)
BEGIN
	SELECT * FROM students_info WHERE reg_no = in_reg_no;
END //

delimiter ;



3. Stored Procedure 3

delimiter //

CREATE PROCEDURE studentUpSert(IN in_reg_no INT, IN in_fnm VARCHAR(50), IN in_mnm VARCHAR(50), IN in_lnm VARCHAR(50))
BEGIN
	DECLARE reg_no_count INT;
	SELECT COUNT(*) INTO reg_no_count FROM students_info WHERE reg_no=in_reg_no;
	IF reg_no_count > 0 THEN
		UPDATE students_info
		SET first_name = in_fnm,
			middle_name = in_mnm,
			last_name = in_lnm
		WHERE reg_no = in_reg_no;
	ELSE
		INSERT INTO students_info VALUES (in_reg_no, in_fnm, in_mnm, in_lnm);
	END IF;
END //

delimiter ;