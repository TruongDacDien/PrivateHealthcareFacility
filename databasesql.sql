CREATE DATABASE DoAn;
USE DoAn;

CREATE TABLE USER
(
	User_Id int PRIMARY KEY AUTO_INCREMENT,
	UserName varchar(40),
	Password varchar(40),
    Email varchar(40),
    Name varchar(40),
    Address varchar(40),
    BirthDate Date,
	SDT VARCHAR(20),
    Gender nvarchar(10),
    Status boolean
	
);

CREATE TABLE  ROLE
(
	Role_Id  int PRIMARY KEY AUTO_INCREMENT,
	Role_1 boolean,
    Role_2 boolean,
    Role_3 boolean,
    Role_4 boolean,
    Role_5 boolean,
    Role_6 boolean,
    Role_7 boolean,
    Position VARCHAR(255),
    User_Id int,
    CONSTRAINT FK_USER FOREIGN KEY(User_Id) REFERENCES USER(User_Id)
);

SELECT * FROM ROLE WHERE  User_Id = 1;
-- Bảng thông tin bệnh nhân
CREATE TABLE BENHNHAN (
    MaBN INT PRIMARY KEY AUTO_INCREMENT,
    HoTen VARCHAR(255),
    NgaySinh DATE,
    GioiTinh VARCHAR(10),
    DiaChi VARCHAR(255),
    SDT VARCHAR(20),
    Image_BN VARCHAR(255),
    Status boolean
);

-- Bảng thông tin bác sĩ
	CREATE TABLE BACSI (
		MaBS INT PRIMARY KEY AUTO_INCREMENT,
		HoTen VARCHAR(255),
		ChuyenMon VARCHAR(255),
		DiaChi VARCHAR(255),
		SDT VARCHAR(20),
		GioiTinh varchar(20),
		Email varchar(40),
		NamKN int,
		Image_BS VARCHAR(255),
		Status boolean
	);

-- Bảng lịch hẹn
CREATE TABLE LICHHEN (
    MaLH INT PRIMARY KEY AUTO_INCREMENT,
    MaBS INT,
    MaBN INT,
    ThoiGianHen datetime,
    TrangThai VARCHAR(20),
    Status boolean,
    FOREIGN KEY (MaBS) REFERENCES BACSI(MaBS),
    FOREIGN KEY (MaBN) REFERENCES BENHNHAN(MaBN)
);
	
-- Bảng phiếu khám
CREATE TABLE PHIEUKHAM (
    MaPK INT PRIMARY KEY AUTO_INCREMENT,
    MaBN INT,
    MaBS INT,
    Id_User int,
    NgayLap DATE,
	TrangThai VARCHAR(20),
	LoiKhuyen TEXT,
    TrieuChung TEXT,
    ChuanDoan TEXT,
    TienKham DECIMAL(10, 2),
	FOREIGN KEY (Id_User) REFERENCES USER(User_Id),
    FOREIGN KEY (MaBN) REFERENCES BENHNHAN(MaBN),
    FOREIGN KEY (MaBS) REFERENCES BACSI(MaBS)
);



-- Bảng toa thuốc
CREATE TABLE TOATHUOC (
    MaToa INT PRIMARY KEY AUTO_INCREMENT,
    MaPK INT,
    NgayKeToa DATE,
    TongTien DECIMAL(10, 2),
    FOREIGN KEY (MaPK) REFERENCES PHIEUKHAM(MaPK)
);

-- Bảng hóa đơn thuốc
CREATE TABLE HOADONPHIEUKHAM (
    MaHD INT PRIMARY KEY AUTO_INCREMENT,
    MaPK INT,
    NgayBan DATE,
    TongTien DECIMAL(10, 2),
    isPaid Boolean,
    FOREIGN KEY (MaPK) REFERENCES PHIEUKHAM(MaPK)
);

-- Tạo dữ liệu mẫu cho bảng HOADONPHIEUKHAM với 20 bản ghi



CREATE TABLE THUOC (
    MaThuoc INT PRIMARY KEY AUTO_INCREMENT,	
    TenThuoc VARCHAR(255),
    DonVi VARCHAR(50),
    SoLuong int,
    DonGia DECIMAL(10, 2),
    NgaySX DATE,
    HanSuDung DATE,
    Status boolean
);



-- Bảng chi tiết toa thuốc
CREATE TABLE CHITIETOATHUOC (
    MaChiTietToa INT PRIMARY KEY AUTO_INCREMENT,
    MaToa INT,
    MaThuoc INT,
    TenThuoc varchar(255),
    SoLuong INT,
    KhoangThoiGian varchar(255),
    LieuLuong varchar(255),
    Tien DECIMAL(10, 2),
    Status boolean,
    FOREIGN KEY (MaToa) REFERENCES TOATHUOC(MaToa),
    FOREIGN KEY (MaThuoc) REFERENCES THUOC(MaThuoc)
);

-- Bảng báo cáo doanh số
CREATE TABLE BAOCAODOANHSO (
    MaBCDoanhSo INT PRIMARY KEY AUTO_INCREMENT,
    NgayLapDoanhSo DATE,
    DoanhSo DECIMAL(15, 2),
     SoLuongPhieuKham int
);




CREATE TABLE DVT
(
	Ma_DVT INT PRIMARY KEY AUTO_INCREMENT,
	Ten_DVT varchar(200),
    Status boolean
);

-- Bảng loại xét nghiệm
CREATE TABLE LOAIXETNGHIEM (
    MaLoaiXN INT PRIMARY KEY AUTO_INCREMENT,
    TenLoaiXN VARCHAR(255),
    ChiPhiXN DECIMAL(10, 2),
    MoTaXN TEXT,
    Status boolean
);

CREATE TABLE XETNGHIEM (
	MaXN int primary key AUTO_INCREMENT,
    MaPK int,
    MaLoaiXN int,
    MaBSXN int,
    TenXN varchar(255),
    KetQua varchar(255),
    ChuanDoan TEXT,
    ChiPhiXN DECIMAL(10, 2),
    Status boolean,
    CONSTRAINT FK_XETNGHIEM1 FOREIGN KEY (MaBSXN) references BACSI(MaBS),
    CONSTRAINT FK_XETNGHIEM2 FOREIGN KEY (MaPK) references PHIEUKHAM(MaPK),
    CONSTRAINT FK_XETNGHIEM3 FOREIGN KEY (MaLoaiXN) references LOAIXETNGHIEM(MaLoaiXN)
);

-- Bảng kết quả xét nghiệm 
CREATE TABLE KETQUAXETNGHIEM (
    MaKQXN INT PRIMARY KEY AUTO_INCREMENT,
    MaXN int,
    MoTa TEXT,
    KetLuan TEXT,
    LoiKhuyen TEXT,
    NgayXN DATE,
	FOREIGN KEY (MaXN) REFERENCES XETNGHIEM(MaXN)
);

CREATE TABLE ANH (
    MaAnh INT PRIMARY KEY AUTO_INCREMENT,
    MaKQXN INT,
    DuongDan text,
    Status boolean,
    FOREIGN KEY (MaKQXN) REFERENCES KETQUAXETNGHIEM(MaKQXN)
);

CREATE TABLE RANGBUOC(
	SoLuongBenhNhanToiDaTrongNgay INT,
    GiaKham DECIMAL(10, 2)
);

INSERT INTO RANGBUOC (SoLuongBenhNhanToiDaTrongNgay, GiaKham) VALUES (0, 0.0);

INSERT INTO USER ( UserName, Password, Email, Name, Address, BirthDate, SDT, Gender, Status) VALUES
( 'Admin', '123456', 'user1@example.com', 'Admin', '123 Main Street, New York, NY', '1985-06-01', '0911223344', 'Male', true),
('user2', 'password2', 'user2@example.com', 'Jane Doe', '456 Elm Street, Los Angeles, CA', '1987-07-02', '0922334455', 'Female', true),
( 'user3', 'password3', 'user3@example.com', 'John Smith', '789 Maple Avenue, Chicago, IL', '1990-08-03', '0933445566', 'Male', true),
('user4', 'password4', 'user4@example.com', 'Emily Johnson', '321 Pine Street, San Francisco, CA', '1992-09-04', '0944556677', 'Female', true),
( 'user5', 'password5', 'user5@example.com', 'Michael Brown', '654 Oak Road, Houston, TX', '1995-10-05', '0955667788', 'Male', true);

INSERT INTO ROLE (Role_1, Role_2, Role_3, Role_4, Role_5, Role_6, Role_7, Position, User_Id)
VALUES 
(true, true, true, true, true, true, true,'', 1),
(false, false, false, false, false, true, true,'', 2),
(false, false, false, false, false, true, true,'', 3),
(false, false, false, false, false, true, true,'', 4),
(false, false, false, false, false, true, true,'', 5);

INSERT INTO BENHNHAN (HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Image_BN, Status) VALUES
('John Smith', '1980-01-01', 'Male', '123 Main Street, New York, NY', '1234567890', 'image1.jpg', true),
('Emily Johnson', '1982-03-15', 'Female', '456 Elm Street, Los Angeles, CA', '2345678901', 'image2.jpg', true),
('Michael Brown', '1984-05-20', 'Male', '789 Maple Avenue, Chicago, IL', '3456789012', 'image3.jpg', true),
('Emma Davis', '1986-07-25', 'Female', '101 Pine Street, San Francisco, CA', '4567890123', 'image4.jpg', true),
('William Wilson', '1988-09-30', 'Male', '234 Oak Road, Houston, TX', '5678901234', 'image5.jpg', true),
('Olivia Taylor', '1990-11-05', 'Female', '345 Cedar Lane, Miami, FL', '6789012345', 'image6.jpg', true),
('James Anderson', '1992-12-10', 'Male', '456 Maple Street, Seattle, WA', '7890123456', 'image7.jpg', true),
('Sophia Martinez', '1994-02-15', 'Female', '567 Willow Avenue, Boston, MA', '8901234567', 'image8.jpg', true),
('Alexander Hernandez', '1996-04-20', 'Male', '678 Pine Lane, Philadelphia, PA', '9012345678', 'image9.jpg', true),
('Isabella Lopez', '1998-06-25', 'Female', '789 Elm Drive, Denver, CO', '0123456789', 'image10.jpg', true),
('Daniel Garcia', '2000-08-30', 'Male', '890 Oak Street, Dallas, TX', '1234567890', 'image11.jpg', true),
('Mia Rodriguez', '2002-10-05', 'Female', '901 Maple Avenue, Portland, OR', '2345678901', 'image12.jpg', true),
('David Martinez', '2004-12-10', 'Male', '012 Cedar Road, Phoenix, AZ', '3456789012', 'image13.jpg', true),
('Ella Gonzalez', '2006-02-15', 'Female', '123 Willow Lane, Las Vegas, NV', '4567890123', 'image14.jpg', true),
('Joseph Hernandez', '2008-04-20', 'Male', '234 Elm Drive, Miami, FL', '5678901234', 'image15.jpg', true),
('Charlotte Ramirez', '2010-06-25', 'Female', '345 Pine Street, Chicago, IL', '6789012345', 'image16.jpg', true),
('Benjamin Torres', '2012-08-30', 'Male', '456 Oak Avenue, Atlanta, GA', '7890123456', 'image17.jpg', true),
('Amelia Flores', '2014-10-05', 'Female', '567 Willow Road, Houston, TX', '8901234567', 'image18.jpg', true),
('Henry Diaz', '2016-12-10', 'Male', '678 Maple Lane, New York, NY', '9012345678', 'image19.jpg', true),
('Sofia Lewis', '2018-02-15', 'Female', '789 Elm Street, Los Angeles, CA', '0123456789', 'image20.jpg', true);

INSERT INTO BACSI (HoTen, ChuyenMon, DiaChi, SDT, GioiTinh, Email, NamKN, Image_BS, Status) VALUES
('Dr. Michael Smith', 'Cardiology', '123 Main Street, New York, NY', '1234567890', 'Male', 'michael.smith@example.com', 15, 'image1.jpg', true),
('Dr. Jennifer Johnson', 'Dermatology', '456 Elm Street, Los Angeles, CA', '2345678901', 'Female', 'jennifer.johnson@example.com', 12, 'image2.jpg', true),
('Dr. William Brown', 'Pediatrics', '789 Maple Avenue, Chicago, IL', '3456789012', 'Male', 'william.brown@example.com', 18, 'image3.jpg', true),
('Dr. Emily Davis', 'Orthopedics', '101 Pine Street, San Francisco, CA', '4567890123', 'Female', 'emily.davis@example.com', 10, 'image4.jpg', true),
('Dr. James Wilson', 'Oncology', '234 Oak Road, Houston, TX', '5678901234', 'Male', 'james.wilson@example.com', 20, 'image5.jpg', true),
('Dr. Olivia Garcia', 'Neurology', '345 Cedar Lane, Miami, FL', '6789012345', 'Female', 'olivia.garcia@example.com', 14, 'image6.jpg', true),
('Dr. Ethan Martinez', 'Psychiatry', '456 Maple Street, Seattle, WA', '7890123456', 'Male', 'ethan.martinez@example.com', 16, 'image7.jpg', true),
('Dr. Sophia Lopez', 'Ophthalmology', '567 Willow Avenue, Boston, MA', '8901234567', 'Female', 'sophia.lopez@example.com', 11, 'image8.jpg', true),
('Dr. Alexander Rodriguez', 'Gastroenterology', '678 Pine Lane, Philadelphia, PA', '9012345678', 'Male', 'alexander.rodriguez@example.com', 19, 'image9.jpg', true),
('Dr. Mia Hernandez', 'ENT', '789 Elm Drive, Denver, CO', '0123456789', 'Female', 'mia.hernandez@example.com', 13, 'image10.jpg', true);

INSERT INTO THUOC (TenThuoc, DonVi, SoLuong, DonGia, NgaySX, HanSuDung, Status)
VALUES
('Acetaminophen', 'Tablet', 100, 10.00, '2023-01-01', '2025-01-01', true),
('Ibuprofen', 'Capsule', 80, 15.50, '2022-12-01', '2024-12-01', true),
('Aspirin', 'Tablet', 120, 8.75, '2023-02-15', '2024-12-15', true),
('Diphenhydramine', 'Liquid', 50, 12.25, '2023-03-10', '2025-03-10', true),
('Loratadine', 'Tablet', 90, 18.00, '2022-11-20', '2024-11-20', true),
('Loperamide', 'Capsule', 60, 20.00, '2023-04-05', '2024-12-05', true),
('Omeprazole', 'Tablet', 70, 25.50, '2023-05-15', '2025-05-15', true),
('Simethicone', 'Liquid', 40, 16.75, '2023-06-20', '2025-06-20', true),
('Ranitidine', 'Tablet', 85, 14.25, '2022-10-10', '2024-10-10', true),
('Meclizine', 'Tablet', 75, 22.00, '2023-07-01', '2025-07-01', true),
('Guaifenesin', 'Liquid', 65, 10.50, '2023-08-05', '2025-08-05', true),
('Dextromethorphan', 'Liquid', 55, 15.75, '2022-12-25', '2024-12-25', true),
('Hydrocortisone', 'Cream', 110, 9.25, '2023-01-10', '2024-12-10', true),
('Miconazole', 'Cream', 95, 14.50, '2023-02-28', '2024-12-28', true),
('Clotrimazole', 'Cream', 105, 12.75, '2023-03-15', '2025-03-15', true),
('Lidocaine', 'Gel', 100, 18.50, '2022-11-30', '2024-11-30', true),
('Benadryl', 'Liquid', 60, 11.00, '2023-04-15', '2025-04-15', true),
('Pepto-Bismol', 'Liquid', 80, 13.25, '2023-05-20', '2025-05-20', true),
('Fexofenadine', 'Tablet', 70, 20.75, '2023-06-10', '2025-06-10', true),
('Cetirizine', 'Tablet', 90, 17.00, '2022-12-05', '2024-12-05', true),
('Esomeprazole', 'Capsule', 55, 22.50, '2023-01-25', '2025-01-25', true),
('Hydroxyzine', 'Tablet', 75, 16.25, '2023-02-15', '2024-12-15', true),
('Famotidine', 'Tablet', 100, 19.00, '2023-03-01', '2025-03-01', true),
('Naproxen', 'Tablet', 85, 14.75, '2022-10-20', '2024-10-20', true),
('Doxylamine', 'Tablet', 65, 11.50, '2023-04-05', '2025-04-05', true),
('Calcium Carbonate', 'Tablet', 75, 9.00, '2023-05-15', '2025-05-15', true),
('Cimetidine', 'Tablet', 95, 16.00, '2023-06-20', '2025-06-20', true),
('Lansoprazole', 'Capsule', 110, 21.25, '2022-11-10', '2024-11-10', true),
('Diphenoxylate', 'Tablet', 120, 17.50, '2023-01-01', '2025-01-01', true),
('Nystatin', 'Tablet', 105, 13.75, '2023-02-10', '2024-12-10', true);

INSERT INTO DVT (Ten_DVT, Status) VALUES
('Tablet', true),
('Capsule', true),
('Liquid', true),
('Cream', true),
('Gel', true);

INSERT INTO LOAIXETNGHIEM (TenLoaiXN, ChiPhiXN, MoTaXN, Status) VALUES
('Blood Test', 100.00, 'Check blood parameters', true),
('Urine Test', 80.00, 'Analyze urine sample', true),
('X-Ray', 200.00, 'Take X-ray images', true),
('MRI', 500.00, 'Magnetic resonance imaging', true),
('CT Scan', 400.00, 'Computed tomography scan', true),
('Ultrasound', 150.00, 'Perform ultrasound imaging', true),
('ECG', 120.00, 'Electrocardiogram test', true),
('Endoscopy', 300.00, 'Internal imaging of body cavities', true),
('Colonoscopy', 350.00, 'Examination of the large intestine', true),
('Biopsy', 250.00, 'Collect tissue sample for analysis', true);

INSERT INTO PHIEUKHAM (MaBN, MaBS, Id_User, NgayLap, TrangThai, LoiKhuyen, TrieuChung, ChuanDoan, TienKham) VALUES
(1, 1, 2, '2024-01-01', 'Completed', 'Maintain a healthy diet.', 'Cough and fever', 'Common cold', 50.00),
(2, 2, 3, '2024-01-05', 'Completed', 'Exercise regularly.', 'Headache and dizziness', 'Migraine', 50.00),
(3, 3, 2, '2024-01-10', 'Completed', 'Stay hydrated.', 'Stomach ache', 'Gastritis', 50.00),
(4, 4, 3, '2024-01-15', 'Completed', 'Get enough sleep.', 'Sore throat', 'Pharyngitis', 50.00),
(5, 5, 2, '2024-01-20', 'Completed', 'Avoid junk food.', 'Back pain', 'Muscle strain', 50.00),
(6, 6, 3, '2024-01-25', 'Completed', 'Practice good posture.', 'Joint pain', 'Arthritis', 50.00),
(7, 7, 2, '2024-01-30', 'Completed', 'Take prescribed medications.', 'Chest pain', 'Angina', 50.00),
(8, 8, 3, '2024-02-05', 'Completed', 'Regular health check-ups.', 'Shortness of breath', 'Asthma', 50.00),
(9, 9, 2, '2024-02-10', 'Completed', 'Reduce stress levels.', 'Fatigue', 'Anemia', 50.00),
(10, 10, 3, '2024-02-15', 'Completed', 'Follow a balanced diet.', 'Blurred vision', 'Glaucoma', 50.00),
(11, 1, 2, '2024-02-20', 'Completed', 'Wear protective gear.', 'Ear pain', 'Otitis', 50.00),
(12, 2, 3, '2024-02-25', 'Completed', 'Use sunscreen.', 'Skin rash', 'Dermatitis', 50.00),
(13, 3, 2, '2024-03-01', 'Completed', 'Monitor blood pressure.', 'Palpitations', 'Arrhythmia', 50.00),
(14, 4, 3, '2024-03-05', 'Completed', 'Limit alcohol intake.', 'Nausea', 'Food poisoning', 50.00),
(15, 5, 2, '2024-03-10', 'Completed', 'Avoid smoking.', 'Weight loss', 'Hyperthyroidism', 50.00),
(16, 6, 3, '2024-03-15', 'Completed', 'Maintain hygiene.', 'Itching', 'Allergic reaction', 50.00),
(17, 7, 2, '2024-03-20', 'Completed', 'Get vaccinated.', 'Fever', 'Influenza', 50.00),
(18, 8, 3, '2024-03-25', 'Completed', 'Limit caffeine.', 'Insomnia', 'Sleep disorder', 50.00),
(19, 9, 2, '2024-03-30', 'Completed', 'Manage diabetes.', 'Increased thirst', 'Diabetes', 50.00),
(20, 10, 3, '2024-04-05', 'Completed', 'Avoid allergens.', 'Sneezing', 'Allergic rhinitis', 50.00),
(1, 1, 2, '2024-04-10', 'Completed', 'Follow exercise regime.', 'Weakness', 'Chronic fatigue', 50.00),
(2, 2, 3, '2024-04-15', 'Completed', 'Regular dental check-ups.', 'Tooth pain', 'Dental caries', 50.00),
(3, 3, 2, '2024-04-20', 'Completed', 'Healthy eating habits.', 'Abdominal pain', 'Irritable bowel syndrome', 50.00),
(4, 4, 3, '2024-04-25', 'Completed', 'Avoid excessive screen time.', 'Eye strain', 'Computer vision syndrome', 50.00),
(5, 5, 2, '2024-04-30', 'Completed', 'Take breaks during work.', 'Neck pain', 'Cervical spondylosis', 50.00),
(6, 6, 3, '2024-05-05', 'Completed', 'Practice mindfulness.', 'Depression', 'Major depressive disorder', 50.00),
(7, 7, 2, '2024-05-10', 'Completed', 'Stay active.', 'Leg pain', 'Peripheral artery disease', 50.00),
(8, 8, 3, '2024-05-15', 'Completed', 'Regular health screenings.', 'High blood pressure', 'Hypertension', 50.00),
(9, 9, 2, '2024-05-20', 'Completed', 'Avoid crowded places.', 'Cough', 'Bronchitis', 50.00),
(1, 10, 3, '2024-05-25', 'Completed', 'Increase fiber intake.', 'Constipation', 'Constipation', 50.00),
(1, 1, 2, '2024-05-30', 'Completed', 'Avoid heavy lifting.', 'Hernia', 'Inguinal hernia', 50.00),
(2, 2, 3, '2024-01-02', 'Completed', 'Maintain social connections.', 'Anxiety', 'Generalized anxiety disorder', 50.00),
(3, 3, 2, '2024-01-06', 'Completed', 'Stay positive.', 'Sadness', 'Dysthymia', 50.00),
(4, 4, 3, '2024-01-12', 'Completed', 'Eat more vegetables.', 'Indigestion', 'Dyspepsia', 50.00),
(5, 5, 2, '2024-01-18', 'Completed', 'Drink herbal tea.', 'Cold symptoms', 'Common cold', 50.00);

INSERT INTO HOADONPHIEUKHAM (MaPK, NgayBan, TongTien, isPaid) VALUES
(1, '2024-01-01', 50.00, true),
(2, '2024-01-05', 50.00, true),
(3, '2024-01-10', 50.00, true),
(4, '2024-01-15', 50.00, true),
(5, '2024-01-20', 50.00, true),
(6, '2024-01-25', 50.00, true),
(7, '2024-01-30', 50.00, true),
(8, '2024-02-05', 50.00, true),
(9, '2024-02-10', 50.00, true),
(10, '2024-02-15', 50.00, true),
(11, '2024-02-20', 50.00, true),
(12, '2024-02-25', 50.00, true),
(13, '2024-03-01', 50.00, true),
(14, '2024-03-05', 50.00, true),
(15, '2024-03-10', 50.00, true),
(16, '2024-03-15', 50.00, true),
(17, '2024-03-20', 50.00, true),
(18, '2024-03-25', 50.00, true),
(19, '2024-03-30', 50.00, true),
(20, '2024-04-05', 50.00, true),
(21, '2024-04-10', 50.00, true),
(22, '2024-04-15', 50.00, true),
(23, '2024-04-20', 50.00, true),
(24, '2024-04-25', 50.00, true),
(25, '2024-04-30', 50.00, true),
(26, '2024-05-05', 50.00, true),
(27, '2024-05-10', 50.00, true),
(28, '2024-05-15', 50.00, true),
(29, '2024-05-20', 50.00, true),
(30, '2024-05-25', 50.00, true),
(31, '2024-05-30', 50.00, true),
(32, '2024-01-02', 50.00, true),
(33, '2024-01-06', 50.00, true),
(34, '2024-01-12', 50.00, true),
(35, '2024-01-18', 50.00, true);
