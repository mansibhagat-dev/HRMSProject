/**
 * HRMS - Human Resource Management System
 * Main JavaScript File
 */

document.addEventListener('DOMContentLoaded', function () {

    // ── Auto-dismiss alerts after 4 seconds ──
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            const bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
            if (bsAlert) bsAlert.close();
        }, 4000);
    });

    // ── Password Toggle for Login Page ──
    const toggleBtn = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('password');
    if (toggleBtn && passwordInput) {
        toggleBtn.addEventListener('click', () => {
            const isPassword = passwordInput.type === 'password';
            passwordInput.type = isPassword ? 'text' : 'password';
            toggleBtn.innerHTML = isPassword
                ? '<i class="bi bi-eye-slash-fill"></i>'
                : '<i class="bi bi-eye-fill"></i>';
        });
    }

    // ── Highlight active nav link based on current URL ──
    const currentPath = window.location.pathname;
    document.querySelectorAll('#mainNavbar .nav-link').forEach(link => {
        const href = link.getAttribute('href');
        if (href && currentPath.startsWith(href) && href !== '/') {
            link.classList.add('active');
        }
    });

    // ── Client-side table search (for employee table) ──
    const tableSearchInput = document.getElementById('tableSearch');
    if (tableSearchInput) {
        tableSearchInput.addEventListener('keyup', function () {
            const keyword = this.value.toLowerCase();
            const rows = document.querySelectorAll('#employeeTable tbody tr');
            rows.forEach(row => {
                const text = row.innerText.toLowerCase();
                row.style.display = text.includes(keyword) ? '' : 'none';
            });
        });
    }

    // ── Form validation feedback ──
    const forms = document.querySelectorAll('form[novalidate]');
    forms.forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    });

    // ── Salary formatter display (optional enhancement) ──
    const salaryInput = document.getElementById('salary');
    if (salaryInput) {
        salaryInput.addEventListener('blur', function () {
            if (this.value && !isNaN(this.value)) {
                // Just ensure 2 decimal places
                this.value = parseFloat(this.value).toFixed(2);
            }
        });
    }

    // ── Date validation: End date must be >= Start date for leave ──
    const startDateInput = document.querySelector('input[name="startDate"]');
    const endDateInput   = document.querySelector('input[name="endDate"]');
    if (startDateInput && endDateInput) {
        startDateInput.addEventListener('change', function () {
            endDateInput.min = this.value;
            if (endDateInput.value && endDateInput.value < this.value) {
                endDateInput.value = this.value;
            }
        });

        // Set today as default minimum
        const today = new Date().toISOString().split('T')[0];
        startDateInput.min = today;
    }

    // ── Tooltips initialization ──
    const tooltipEls = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    tooltipEls.forEach(el => new bootstrap.Tooltip(el));

});

// ── Delete Confirmation Modal ──
function confirmDelete(id, name, type) {
    const modal = document.getElementById('deleteModal');
    const nameEl = document.getElementById('deleteItemName');
    const confirmBtn = document.getElementById('deleteConfirmBtn');

    if (!modal || !nameEl || !confirmBtn) return;

    nameEl.textContent = name;
    confirmBtn.href = `/${type}s/delete/${id}`;

    const bsModal = new bootstrap.Modal(modal);
    bsModal.show();
}

// ── Confirm action with custom message ──
function confirmAction(message) {
    return confirm(message || 'Are you sure you want to proceed?');
}
