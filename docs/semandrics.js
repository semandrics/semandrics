// Theme
const saved = localStorage.getItem('theme') || 'dark';
document.body.className = saved;

function toggleTheme() {
  const next = document.body.classList.contains('light') ? 'dark' : 'light';
  document.body.className = next;
  localStorage.setItem('theme', next);
}

// Navigation
function showSection(id, btn) {
  document.querySelectorAll('.section').forEach(s => s.classList.remove('active'));
  document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
  document.getElementById(id).classList.add('active');
  if (btn) btn.classList.add('active');
  window.scrollTo({ top: 0, behavior: 'smooth' });
}
