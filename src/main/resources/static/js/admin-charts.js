// Lakshmi Narayana Travels - PowerBI Chart.js Engine

function initPowerBICharts(chartData) {
    if (!chartData) return;

    // 1. Revenue Area Chart (PowerBI Gradient)
    const revCtx = document.getElementById('revenueChart');
    if (revCtx) {
        const gradient = revCtx.getContext('2d').createLinearGradient(0, 0, 0, 300);
        gradient.addColorStop(0, 'rgba(37, 99, 235, 0.35)');
        gradient.addColorStop(1, 'rgba(37, 99, 235, 0.0)');

        new Chart(revCtx, {
            type: 'line',
            data: {
                labels: chartData.revenueLabels || ['Nov', 'Dec', 'Jan', 'Feb', 'Mar'],
                datasets: [{
                    label: 'Revenue (INR)',
                    data: chartData.revenueData || [28500, 42000, 56000, 68000, 78500],
                    borderColor: '#2563eb',
                    backgroundColor: gradient,
                    borderWidth: 3,
                    fill: true,
                    tension: 0.4,
                    pointBackgroundColor: '#2563eb',
                    pointRadius: 5,
                    pointHoverRadius: 8
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                return ' ₹ ' + context.parsed.y.toLocaleString('en-IN');
                            }
                        }
                    }
                },
                scales: {
                    y: {
                        grid: { color: '#f1f5f9' },
                        ticks: {
                            callback: function(val) { return '₹' + (val / 1000) + 'k'; }
                        }
                    },
                    x: { grid: { display: false } }
                }
            }
        });
    }

    // 2. Fuel Type Breakdown Bar Chart
    const fuelCtx = document.getElementById('fuelChart');
    if (fuelCtx) {
        new Chart(fuelCtx, {
            type: 'bar',
            data: {
                labels: chartData.fuelLabels || ['Diesel', 'Petrol', 'CNG', 'Electric'],
                datasets: [{
                    label: 'Cars in Fleet',
                    data: chartData.fuelData || [4, 2, 2, 1],
                    backgroundColor: ['#0284c7', '#ea580c', '#059669', '#7c3aed'],
                    borderRadius: 8
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: { legend: { display: false } },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: { stepSize: 1 }
                    },
                    x: { grid: { display: false } }
                }
            }
        });
    }

    // 3. Seater Distribution Doughnut Chart (5 Seater vs 7 Seater)
    const seaterCtx = document.getElementById('seaterChart');
    if (seaterCtx) {
        new Chart(seaterCtx, {
            type: 'doughnut',
            data: {
                labels: chartData.seaterLabels || ['5 Seater', '7 Seater'],
                datasets: [{
                    data: chartData.seaterData || [5, 4],
                    backgroundColor: ['#3b82f6', '#f59e0b', '#10b981'],
                    borderWidth: 2,
                    borderColor: '#ffffff'
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { position: 'bottom' }
                },
                cutout: '68%'
            }
        });
    }

    // 4. Booking Status Breakdown
    const statusCtx = document.getElementById('statusChart');
    if (statusCtx) {
        new Chart(statusCtx, {
            type: 'bar',
            data: {
                labels: chartData.statusLabels || ['Approved', 'Pending', 'Ongoing', 'Completed', 'Cancelled'],
                datasets: [{
                    label: 'Bookings Count',
                    data: chartData.statusData || [3, 2, 1, 4, 1],
                    backgroundColor: ['#10b981', '#f59e0b', '#06b6d4', '#64748b', '#ef4444'],
                    borderRadius: 6
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: { legend: { display: false } },
                scales: {
                    y: { beginAtZero: true, ticks: { stepSize: 1 } },
                    x: { grid: { display: false } }
                }
            }
        });
    }
}

