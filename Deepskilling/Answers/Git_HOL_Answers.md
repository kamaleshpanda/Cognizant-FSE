# Git Hands-On Labs - Answers


---

## Lab 1: Basic Git Configuration & Workflow (Git-HOL 1)

### Objectives
- Configure Git global user details.
- Set Notepad++ as default editor.
- Initialize local repository, commit changes, and push to remote.

### Commands & Steps

```bash
# Step 1: User Configuration
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Verify global configuration
git config --global --list

# Step 2: Set Notepad++ as Default Editor (Windows)
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
git config --global -e

# Step 3: Repository Setup & First Commit
mkdir GitDemo
cd GitDemo
git init

# Verify hidden .git folder
ls -la

# Create file welcome.txt and check status
echo "Welcome to Git Hands On Lab" > welcome.txt
git status

# Stage the file
git add welcome.txt

# Commit with default editor (opens Notepad++)
git commit

# Push to Remote Repository (GitLab/GitHub)
git remote add origin https://gitlab.com/username/GitDemo.git
git pull origin master
git push -u origin master
```

---

## Lab 2: Ignoring Files using `.gitignore` (Git-HOL 2)

### Objectives
- Prevent tracking temporary log files and build folders.

### Commands & Steps

```bash
# Create unwanted log files and log directory
echo "App log data" > app.log
mkdir log
echo "Debug log" > log/debug.log

# Create .gitignore file
notepad++ .gitignore
```

### Content of `.gitignore`:
```text
*.log
log/
```

```bash
# Verify Git status (app.log and log/ directory should NOT appear in untracked files)
git status

# Add and commit .gitignore
git add .gitignore
git commit -m "Added .gitignore to ignore log files and log directory"
```

---

## Lab 3: Branching & Merging (Git-HOL 3)

### Objectives
- Create a feature branch, commit changes, and merge into master branch.

### Commands & Steps

```bash
# Check current state of master
git status

# Create and switch to new branch
git branch GitNewBranch
git checkout GitNewBranch

# List all branches (* indicates active branch)
git branch -a

# Make changes in branch
echo "Feature updates" > feature.txt
git add feature.txt
git commit -m "Added feature.txt in GitNewBranch"

# Switch back to master
git checkout master

# Check difference between master and branch
git diff master..GitNewBranch

# Merge branch into master
git merge GitNewBranch

# Check log graph
git log --oneline --graph --decorate

# Delete branch after successful merge
git branch -d GitNewBranch
```

---

## Lab 4: Merge Conflict Resolution (Git-HOL 4)

### Objectives
- Handle merge conflicts when same file is edited concurrently in master and branch.

### Commands & Steps

```bash
# Create branch GitWork
git checkout -b GitWork

# Create and commit hello.xml in branch
echo "<note><to>User</to><from>Branch</from></note>" > hello.xml
git add hello.xml
git commit -m "Added hello.xml in GitWork branch"

# Switch back to master and modify hello.xml differently
git checkout master
echo "<note><to>User</to><from>Master</from></note>" > hello.xml
git add hello.xml
git commit -m "Added hello.xml in master branch"

# Try merging branch into master (Conflict will occur!)
git merge GitWork

# Open hello.xml to resolve conflict manually
# Conflict marker in hello.xml:
# <<<<<<< HEAD
# <note><to>User</to><from>Master</from></note>
# =======
# <note><to>User</to><from>Branch</from></note>
# >>>>>>> GitWork

# Keep final resolved content:
echo "<note><to>User</to><from>Resolved</from></note>" > hello.xml

# Mark conflict as resolved and commit
git add hello.xml
git commit -m "Resolved merge conflict in hello.xml"

# Clean up branch
git branch -d GitWork
```

---

## Lab 5: Clean Up & Remote Repository Sync (Git-HOL 5)

### Objectives
- Synchronize local master branch with remote repository and push changes.

### Commands & Steps

```bash
# Ensure clean working directory
git status

# Fetch and pull updates from remote master
git pull origin master

# Push all local commits to remote master
git push origin master

# Verify status
git status
```
