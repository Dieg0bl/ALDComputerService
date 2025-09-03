# Repository Configuration

## Repository Status
- **Visibility**: Public
- **Status**: Archived
- **Main Branch**: Protected

## Branch Protection Rules

### Main Branch Protection
- Direct pushes to main branch are prohibited
- Pull requests are required for all changes
- Status checks must pass before merging
- Enforce for administrators: Yes

## Archive Configuration
This repository is configured to be archived, meaning:
- Repository is read-only
- Issues, pull requests, and wiki are disabled for contributions
- Repository remains publicly accessible for viewing and cloning
- All existing content is preserved

## Administrative Settings
- **Default Branch**: main
- **Issues**: Disabled (due to archive status)
- **Wiki**: Disabled (due to archive status)
- **Projects**: Disabled (due to archive status)
- **Security Alerts**: Enabled (if supported for archived repos)

## Implementation Notes
- Branch protection is enforced via GitHub Actions workflow
- Repository visibility and archive status require GitHub web interface configuration
- This configuration serves as documentation for intended repository state