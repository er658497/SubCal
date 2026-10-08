# SubCal

A shared subcontractor scheduling app for remodeling project managers.

## Current behavior

- First use asks for first and last name; the device remembers the identity.
- Week View shows Monday-Friday projects and lets each project/day contain multiple trades.
- Every trade assignment records the scheduler automatically and displays their initials.
- Removing another person's assignment requires confirmation and creates an in-app notification for that scheduler.
- All Projects is the default; My Jobs filters to projects owned by the current user.
- Calendar View lists the trades scheduled on each workday, without project names.
- Edit Subs manages trade names and colors.
- Shared state syncs through Supabase.

The Android app bundles the same web UI so the web and Android versions use the same scheduling behavior.

## Supabase

Run `supabase.sql` in Supabase SQL Editor. Configure `SUPABASE_URL`, `SUPABASE_ANON_KEY`, and optional `SCHEDULE_ID` as GitHub Actions secrets or Gradle properties.

## Build

GitHub Actions builds the Android debug APK.
