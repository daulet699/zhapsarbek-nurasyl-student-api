#!/usr/bin/env bash
set -euo pipefail
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
LOG="$PROJECT_DIR/logs/Zhapsarbek_Nurasyl_system_report.log"
mkdir -p "$PROJECT_DIR/logs"
{
  echo "Student: Nurasyl Zhapsarbek"
  echo "Group: IT2-2308 | ID: 37841"
  echo "Date and time: $(date)"
  echo "Username: $(whoami)"
  echo "OS: $(grep '^PRETTY_NAME=' /etc/os-release | cut -d= -f2- | tr -d '"')"
  echo "Kernel: $(uname -r)"
  echo "CPU cores: $(nproc)"
  echo 'CPU details:'
  if command -v lscpu >/dev/null 2>&1; then lscpu | head -n 15; else head -n 8 /proc/cpuinfo; fi
  echo 'Memory:'
  free -h
  echo 'Disk usage:'
  df -h "$PROJECT_DIR"
  echo 'Available disk space:'
  df -h "$PROJECT_DIR" | awk 'NR==2 {print $4}'
} | tee "$LOG"
