-- ============================================================
-- FitnessCare Seed Data
-- ============================================================
-- Passwords are SHA-256 hashed. All demo passwords are: password123
-- SHA-256("password123") = ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f

USE fitnesscare;

-- ======================== ADMIN ======================== admin123 password
INSERT INTO admin_gym (id, password, name) VALUES 
('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'Vasu');

-- ======================== MANAGERS ========================
INSERT INTO managerlogintable_gym (managerid, password, name, gender) VALUES
('MGR001', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Priya', 'Female'),
('MGR002', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Dixita', 'Female');

-- ======================== MEMBERS ========================
INSERT INTO user_gym (id, name, password, gender, program, fitness_goal, age) VALUES
('MEM001', 'Narendra Modi', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Male', 'Weight Training', 'Muscle Building', 25),
('MEM002', 'Amit Shah', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Male', 'Yoga', 'General Fitness', 28),
('MEM003', 'Yogi Adityanath', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Male', 'Cardio', 'Weight Loss', 32),
('MEM004', 'Rahul Gandhi', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Male', 'General Fitness', 'Improve Endurance', 22),
('MEM005', 'Arvind Kejriwal', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'Male', 'Strength Training', 'Weight Gain', 27);

-- ======================== DIET PLANS ========================
INSERT INTO diet_plan (plan_name, goal, breakfast, mid_morning_meal, lunch, evening_snack, dinner, additional_instructions, created_by) VALUES
('Muscle Builder Plan', 'Muscle Building',
 '6 egg whites, 2 whole eggs, oatmeal with banana, glass of milk',
 'Protein shake with almonds and a banana',
 'Grilled chicken breast (200g), brown rice (1 cup), mixed vegetables, dal',
 'Greek yogurt with mixed nuts and honey',
 'Fish or paneer (200g), quinoa or roti (2), steamed broccoli, salad',
 'Drink at least 3-4 liters of water daily. Take whey protein post-workout. Eat every 3 hours.',
 'admin'),

('Weight Loss Plan', 'Weight Loss',
 'Oats with skim milk, 2 boiled eggs, green tea',
 'Apple with 10 almonds',
 'Grilled fish or chicken (150g), salad with olive oil dressing, 1 roti',
 'Sprout salad or roasted chana',
 'Soup, grilled vegetables, small portion of brown rice or 1 roti',
 'Avoid sugar and processed foods. Do 30 min cardio daily. No eating after 8 PM.',
 'admin'),

('General Fitness Plan', 'General Fitness',
 'Whole wheat toast with peanut butter, fruit, glass of juice',
 'Mixed fruit bowl',
 'Dal, rice, roti, sabzi, curd, salad',
 'Tea with 2-3 biscuits or a fruit',
 'Light dinner - khichdi or roti with sabzi, glass of milk',
 'Maintain balanced diet. Exercise 3-4 times a week. Stay hydrated.',
 'MGR001'),

('Yoga Diet Plan', 'General Fitness',
 'Fresh fruit smoothie, soaked almonds, herbal tea',
 'Coconut water with dates',
 'Vegetable curry, brown rice, buttermilk, salad',
 'Fruit salad or trail mix',
 'Light soup, whole wheat roti, steamed vegetables',
 'Eat sattvic food. Avoid heavy meals before yoga sessions. Practice mindful eating.',
 'MGR001'),

('Endurance Booster Plan', 'Improve Endurance',
 'Banana pancakes, peanut butter, orange juice, boiled eggs',
 'Energy bar with electrolyte drink',
 'Pasta with lean meat sauce, grilled vegetables, whole grain bread',
 'Trail mix with dried fruits',
 'Grilled salmon or tofu, sweet potato, steamed greens',
 'Focus on complex carbohydrates. Stay hydrated during workouts. Take B-complex vitamins.',
 'MGR002');

-- ======================== DIET PLAN ASSIGNMENTS ========================
INSERT INTO member_diet_plan (member_id, diet_plan_id) VALUES
('MEM001', 1),
('MEM002', 4),
('MEM003', 2),
('MEM004', 5),
('MEM005', 3);

-- ======================== PROGRESS RECORDS ========================
-- MEM001: Muscular build, Normal BMI
INSERT INTO progress_record (member_id, weight_kg, height_cm, bmi, bmi_status, record_date, notes) VALUES
('MEM001', 78.00, 175.00, 25.47, 'Overweight', '2025-06-01', 'Starting measurement. Focus on lean muscle gain.'),
('MEM001', 79.50, 175.00, 25.96, 'Overweight', '2025-06-15', 'Gained 1.5kg muscle mass. Strength improving.'),
('MEM001', 80.00, 175.00, 26.12, 'Overweight', '2025-07-01', 'Good progress on bench press and squats.');

-- MEM002: Yoga practitioner, Normal BMI
INSERT INTO progress_record (member_id, weight_kg, height_cm, bmi, bmi_status, record_date, notes) VALUES
('MEM002', 58.00, 162.00, 22.10, 'Normal', '2025-06-01', 'Initial assessment. Flexibility is good.'),
('MEM002', 57.50, 162.00, 21.91, 'Normal', '2025-07-01', 'Maintaining healthy weight. Flexibility improved.');

-- MEM003: Weight loss journey, Obese -> Overweight
INSERT INTO progress_record (member_id, weight_kg, height_cm, bmi, bmi_status, record_date, notes) VALUES
('MEM003', 98.00, 170.00, 33.91, 'Obese', '2025-05-01', 'Starting weight loss program. High motivation.'),
('MEM003', 94.00, 170.00, 32.53, 'Obese', '2025-06-01', 'Lost 4kg in first month. Diet compliance good.'),
('MEM003', 90.00, 170.00, 31.14, 'Obese', '2025-07-01', 'Steady progress. Cardio endurance improving.');

-- MEM004: Underweight, endurance focus
INSERT INTO progress_record (member_id, weight_kg, height_cm, bmi, bmi_status, record_date, notes) VALUES
('MEM004', 48.00, 165.00, 17.63, 'Underweight', '2025-06-15', 'Need to increase caloric intake alongside endurance training.'),
('MEM004', 49.50, 165.00, 18.18, 'Underweight', '2025-07-10', 'Slowly gaining weight. Endurance improving.');

-- MEM005: Weight gain, Normal
INSERT INTO progress_record (member_id, weight_kg, height_cm, bmi, bmi_status, record_date, notes) VALUES
('MEM005', 62.00, 178.00, 19.57, 'Normal', '2025-06-01', 'Starting strength training for weight gain.'),
('MEM005', 64.00, 178.00, 20.20, 'Normal', '2025-07-01', 'Gained 2kg. Strength increasing across all lifts.');

-- ======================== COMPLAINTS ========================
INSERT INTO complaint_gym (id, member_id, name, subject, complaint, status, created_at, updated_at) VALUES
('CMP001', 'MEM001', 'Narendra Modi', 'Equipment Maintenance',
 'The bench press in section B has a loose bolt. It wobbles during use and could be dangerous. Please fix it urgently.',
 'RESOLVED', '2025-06-10 10:30:00', '2025-06-11 14:00:00'),

('CMP002', 'MEM002', 'Amit Shah', 'Yoga Room Cleanliness',
 'The yoga room mats are not being cleaned regularly. Some mats have stains and smell. Please ensure daily cleaning.',
 'RESOLVED', '2025-06-15 09:00:00', '2025-06-16 11:00:00'),

('CMP003', 'MEM003', 'Yogi Adityanath', 'Locker Room Issue',
 'My assigned locker (#45) lock is broken. Anyone can open it. I need a replacement lock or a new locker assignment.',
 'PENDING', '2025-07-01 16:45:00', NULL),

('CMP004', 'MEM001', 'Narendra Modi', 'Water Cooler Not Working',
 'The water cooler near the weight training area has been out of order for 3 days. We need hydration during workouts.',
 'PENDING', '2025-07-05 11:20:00', NULL),

('CMP005', 'MEM004', 'Rahul Gandhi', 'Trainer Availability',
 'I joined the endurance program but the assigned trainer is often not available during my scheduled time (6-7 PM).',
 'REJECTED', '2025-06-20 18:30:00', '2025-06-22 10:00:00'),

('CMP006', 'MEM005', 'Arvind Kejriwal', 'AC Not Working',
 'The air conditioning in the main gym hall is not working properly. It gets very hot during afternoon sessions.',
 'PENDING', '2025-07-08 14:00:00', NULL);
