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

4. Stored Procedure 4

delimiter //

CREATE PROCEDURE studentUpSert2(
	IN in_reg_no INT,
	IN in_fnm VARCHAR(50),
	IN in_mnm VARCHAR(50),
	IN in_lnm VARCHAR(50),
	IN in_g_fnm VARCHAR(50),
	IN in_g_mnm VARCHAR(50),
	IN in_g_lnm VARCHAR(50),
	IN in_is_admin VARCHAR(1),
	IN in_password VARCHAR(50))
BEGIN
	DECLARE reg_no_count INT;
	SELECT COUNT(*) INTO reg_no_count FROM students_info WHERE reg_no=in_reg_no;
	IF reg_no_count > 0 THEN
		START TRANSACTION;
		UPDATE students_info
		SET first_name = in_fnm,
			middle_name = in_mnm,
			last_name = in_lnm
		WHERE reg_no = in_reg_no;
		
		UPDATE guardian_info
		SET guardian_first_name = in_g_fnm,
			guardian_middle_name = in_g_mnm,
			guardian_last_name = in_g_lnm
		WHERE reg_no = in_reg_no;
		
		UPDATE students_other_info
		SET is_admin = in_is_admin,
			password = in_password
		WHERE reg_no = in_reg_no;
		COMMIT;
	ELSE
		START TRANSACTION;
		INSERT INTO students_info VALUES (in_reg_no, in_fnm, in_mnm, in_lnm);
		INSERT INTO guardian_info VALUES (in_reg_no, in_g_fnm, in_g_mnm, in_g_lnm);
		INSERT INTO students_other_info VALUES (in_reg_no, in_is_admin, in_password);
		COMMIT;
	END IF;
END //

delimiter ;