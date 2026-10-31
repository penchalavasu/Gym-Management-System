package com.fitnesscare.util;

/**
 * Input validation utility for form data.
 */
public class ValidationUtil {

    /**
     * Check if a string is null or empty after trimming.
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Check if a string exceeds a maximum length.
     */
    public static boolean exceedsMaxLength(String value, int maxLength) {
        return value != null && value.length() > maxLength;
    }

    /**
     * Check if a value is a valid positive decimal number.
     */
    public static boolean isValidPositiveDecimal(String value) {
        if (isEmpty(value)) return false;
        try {
            double d = Double.parseDouble(value.trim());
            return d > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if a value is a valid positive integer.
     */
    public static boolean isValidPositiveInt(String value) {
        if (isEmpty(value)) return false;
        try {
            int i = Integer.parseInt(value.trim());
            return i > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if age is reasonable (1-120).
     */
    public static boolean isValidAge(String value) {
        if (isEmpty(value)) return true; // age is optional
        try {
            int age = Integer.parseInt(value.trim());
            return age >= 1 && age <= 120;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if a gender value is valid.
     */
    public static boolean isValidGender(String gender) {
        if (isEmpty(gender)) return false;
        String g = gender.trim().toLowerCase();
        return g.equals("male") || g.equals("female") || g.equals("other");
    }

    /**
     * Check if a program value is valid.
     */
    public static boolean isValidProgram(String program) {
        if (isEmpty(program)) return false;
        String p = program.trim();
        return p.equals("General Fitness") || p.equals("Weight Training") ||
               p.equals("Cardio") || p.equals("Yoga") || p.equals("Strength Training");
    }

    /**
     * Check if a fitness goal value is valid.
     */
    public static boolean isValidFitnessGoal(String goal) {
        if (isEmpty(goal)) return true; // optional
        String g = goal.trim();
        return g.equals("Weight Loss") || g.equals("Weight Gain") ||
               g.equals("Muscle Building") || g.equals("General Fitness") ||
               g.equals("Improve Endurance");
    }

    /**
     * Check if a complaint status is valid.
     */
    public static boolean isValidComplaintStatus(String status) {
        if (isEmpty(status)) return false;
        String s = status.trim().toUpperCase();
        return s.equals("PENDING") || s.equals("RESOLVED") || s.equals("REJECTED");
    }

    /**
     * Sanitize string for HTML output (basic XSS prevention).
     */
    public static String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                     .replace("<", "&lt;")
                     .replace(">", "&gt;")
                     .replace("\"", "&quot;")
                     .replace("'", "&#x27;");
    }

    /**
     * Calculate BMI from weight (kg) and height (cm).
     */
    public static double calculateBMI(double weightKg, double heightCm) {
        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    /**
     * Determine BMI status classification.
     */
    public static String getBMIStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25.0) return "Normal";
        if (bmi < 30.0) return "Overweight";
        return "Obese";
    }
}
