/**
 * FitnessCare — Main JavaScript
 */

// ---- Sidebar Toggle (Mobile) ----
document.addEventListener('DOMContentLoaded', function() {
    const toggle = document.getElementById('sidebarToggle');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('sidebarOverlay');

    if (toggle && sidebar) {
        toggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            if (overlay) overlay.classList.toggle('active');
        });
    }

    if (overlay) {
        overlay.addEventListener('click', function() {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        });
    }

    // ---- Alert Auto-dismiss ----
    const alerts = document.querySelectorAll('.fc-alert');
    alerts.forEach(function(alert) {
        // Auto dismiss after 5 seconds
        setTimeout(function() {
            alert.style.transition = 'opacity 0.3s';
            alert.style.opacity = '0';
            setTimeout(function() { alert.remove(); }, 300);
        }, 5000);

        // Manual close
        const closeBtn = alert.querySelector('.alert-close');
        if (closeBtn) {
            closeBtn.addEventListener('click', function() {
                alert.style.transition = 'opacity 0.3s';
                alert.style.opacity = '0';
                setTimeout(function() { alert.remove(); }, 300);
            });
        }
    });
});

// ---- Confirm Delete Dialog ----
function confirmDelete(formId, itemName) {
    var overlay = document.getElementById('confirmOverlay');
    var message = document.getElementById('confirmMessage');
    var confirmBtn = document.getElementById('confirmYes');

    if (message) {
        message.textContent = 'Are you sure you want to delete ' + (itemName || 'this item') + '? This action cannot be undone.';
    }

    if (overlay) overlay.classList.add('active');

    if (confirmBtn) {
        confirmBtn.onclick = function() {
            document.getElementById(formId).submit();
        };
    }
}

function closeConfirm() {
    var overlay = document.getElementById('confirmOverlay');
    if (overlay) overlay.classList.remove('active');
}

// ---- Confirm Status Change ----
function confirmStatusChange(formId, status) {
    var overlay = document.getElementById('confirmOverlay');
    var message = document.getElementById('confirmMessage');
    var confirmBtn = document.getElementById('confirmYes');

    if (message) {
        message.textContent = 'Are you sure you want to mark this complaint as ' + status + '?';
    }

    if (overlay) overlay.classList.add('active');

    if (confirmBtn) {
        confirmBtn.onclick = function() {
            document.getElementById(formId).submit();
        };
    }
}

// ---- Client-side form validation helpers ----
function validateRequired(fieldId, errorMsg) {
    var field = document.getElementById(fieldId);
    if (!field || !field.value.trim()) {
        alert(errorMsg || 'This field is required.');
        if (field) field.focus();
        return false;
    }
    return true;
}

function validatePasswordMatch(passId, confirmId) {
    var pass = document.getElementById(passId);
    var confirm = document.getElementById(confirmId);
    if (pass && confirm && pass.value !== confirm.value) {
        alert('Passwords do not match.');
        confirm.focus();
        return false;
    }
    return true;
}
