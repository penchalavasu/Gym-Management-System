<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Create a FitnessCare account - Member Registration">
    <title>Create Account - FitnessCare</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
</head>
<body>
    <div class="auth-wrapper">
        <div class="auth-card register-card">
            <div class="auth-brand">
                <i class="bi bi-person-plus-fill auth-icon register-icon"></i>
                <div class="auth-title">Create Account</div>
                <div class="auth-subtitle">Register as a new member</div>
            </div>

            <c:if test="${not empty error}">
                <div class="fc-alert fc-alert-error" style="margin-bottom:14px;">
                    <span>${error}</span>
                    <button class="alert-close">&times;</button>
                </div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/register" id="registerForm"
                  onsubmit="return validatePasswordMatch('password','confirmPassword');">
                <div class="form-group">
                    <label for="memberId">Member ID</label>
                    <input type="text" id="memberId" name="memberId" class="form-control"
                           placeholder="e.g., MEM006" required maxlength="45"
                           value="${memberId}">
                    <span class="form-text">Choose a unique Member ID</span>
                </div>
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" class="form-control"
                           placeholder="Enter your full name" required maxlength="100"
                           value="${name}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="Min 6 characters" required minlength="6">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" class="form-control"
                           placeholder="Re-enter your password" required>
                </div>
                <div class="form-group">
                    <label>Gender</label>
                    <div style="display:flex;gap:16px;margin-top:4px;">
                        <div class="form-check">
                            <input type="radio" name="gender" id="genderMale" value="Male"
                                   ${gender == 'Male' ? 'checked' : ''} required>
                            <label for="genderMale">Male</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" name="gender" id="genderFemale" value="Female"
                                   ${gender == 'Female' ? 'checked' : ''}>
                            <label for="genderFemale">Female</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" name="gender" id="genderOther" value="Other"
                                   ${gender == 'Other' ? 'checked' : ''}>
                            <label for="genderOther">Other</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="program">Fitness Program</label>
                    <select id="program" name="program" class="form-control" required>
                        <option value="">-- Select Program --</option>
                        <option value="General Fitness" ${program == 'General Fitness' ? 'selected' : ''}>General Fitness</option>
                        <option value="Weight Training" ${program == 'Weight Training' ? 'selected' : ''}>Weight Training</option>
                        <option value="Cardio" ${program == 'Cardio' ? 'selected' : ''}>Cardio</option>
                        <option value="Yoga" ${program == 'Yoga' ? 'selected' : ''}>Yoga</option>
                        <option value="Strength Training" ${program == 'Strength Training' ? 'selected' : ''}>Strength Training</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fitnessGoal">Fitness Goal</label>
                    <select id="fitnessGoal" name="fitnessGoal" class="form-control">
                        <option value="">-- Select Goal --</option>
                        <option value="Weight Loss" ${fitnessGoal == 'Weight Loss' ? 'selected' : ''}>Weight Loss</option>
                        <option value="Weight Gain" ${fitnessGoal == 'Weight Gain' ? 'selected' : ''}>Weight Gain</option>
                        <option value="Muscle Building" ${fitnessGoal == 'Muscle Building' ? 'selected' : ''}>Muscle Building</option>
                        <option value="General Fitness" ${fitnessGoal == 'General Fitness' ? 'selected' : ''}>General Fitness</option>
                        <option value="Improve Endurance" ${fitnessGoal == 'Improve Endurance' ? 'selected' : ''}>Improve Endurance</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="age">Age <span style="font-weight:400;color:#999;">(optional)</span></label>
                    <input type="number" id="age" name="age" class="form-control"
                           placeholder="Enter your age" min="1" max="120"
                           value="${age}">
                </div>
                <button type="submit" class="btn-register">
                    <i class="bi bi-person-plus"></i> Create Account
                </button>
            </form>

            <div class="auth-footer">
                Already have an account? <a href="${pageContext.request.contextPath}/login">Sign in</a>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>
