#!/usr/bin/env bash
set -euo pipefail
leetcode_root=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
build_dir=$(mktemp -d "${TMPDIR:-/tmp}/leetcode-pattern-tests.XXXXXX")
# Remove only the fresh compilation directory, never any study files.
trap 'if [[ -n "$build_dir" && -d "$build_dir" ]]; then rm -r -- "$build_dir"; fi' EXIT
javac -encoding UTF-8 -d "$build_dir" "$leetcode_root/tests/Verify.java"
java -cp "$build_dir" Verify "$leetcode_root" "$build_dir" "$@"
