# CST Lectures 

Welcome to **CST Lectures** — a central hub for all courses offered by CST College.  
Each course is in its own repository. Click any course to view, clone, or download it individually.  

---

## Information Technology Courses

| Course Code | Course Name | Repository |
|------------|-------------|------------|
| CPL101-PY | Introduction To Programming (Python) | [View Repository](https://github.com/ChetanTimsina/CPL101-Introduction-To-Programming-Python-.git) |
| CPL101-C | Introduction To Programming (C) | [View Repository](https://github.com/ChetanTimsina/CPL101-Introduction-To-Programming-C-Program-.git) |
| CPL102-JAVA | Object-Oriented Programming (Java) | [View Repository](https://github.com/ChetanTimsina/CPL102-Object-Oriented-Programming-Java-.git) |
| CTE202 | Data Structure & Algorithms | [View Repository](https://github.com/ChetanTimsina/CTE202-Data-Structure---Algorithms.git) |
| CTE203 | Human Computer Interaction | [View Repository](https://github.com/ChetanTimsina/CTE203-Human-Computer-Interaction.git) |
| CTE204 | Web Application Development | [View Repository](https://github.com/ChetanTimsina/CTE204-Web-Application-Development.git) |
| CTE205 | Operating System | [View Repository](https://github.com/ChetanTimsina/CTE205-Operating-System.git) |
| CTE206 | Software Engineering | [View Repository](https://github.com/ChetanTimsina/CTE206-Software-Engineering.git) |
| CTE207 | Artificial Intelligence | [View Repository](https://github.com/ChetanTimsina/CTE207-Artificial-Intelligence.git) |
| CTE208-FLUTTER | Mobile Application Development (Flutter) | [View Repository](https://github.com/ChetanTimsina/CTE208-Mobile-Application-Developement-Flutter-.git) |
| CTE309-ML | Machine Learning AI Model Training | [View Repository](https://github.com/ChetanTimsina/CTE309-Machine-Learning-AI-Model-Training-.git) |
| DIS101 | Database Systems | [View Repository](https://github.com/ChetanTimsina/DIS101-Database-Systems.git) |
| DIS302 | Data Scalability & Analytics | [View Repository](https://github.com/ChetanTimsina/DIS302-Data-Scalability---Analytics.git) |
| DIS303 | Cryptology & Cryptanalysis | [View Repository](https://github.com/ChetanTimsina/DIS303-Cryptology---Cryptanalysis.git) |
| ITM301 | Professional Practices in IT | [View Repository](https://github.com/ChetanTimsina/ITM301-Professional-Practices-in-IT.git) |
| ITM302-LINUX | System Administration (Linux) | [View Repository](https://github.com/ChetanTimsina/ITM302-System-Administration-Linux-.git) |
| ITM304 | Project Management | [View Repository](https://github.com/ChetanTimsina/ITM304-Project-Management.git) |
| MINI-PROJECT | Mini Project | [View Repository](https://github.com/ChetanTimsina/MiniProject.git) |
| NWC201 | Computer Communication Networks | [View Repository](https://github.com/ChetanTimsina/NWC201-Computer-Communication-Networks.git) |
| NWC202 | Introduction to Internet of Things | [View Repository](https://github.com/ChetanTimsina/NWC202-Introduction-to-Internet-of-Things.git) |
| NWC303 | Advanced Routing | [View Repository](https://github.com/ChetanTimsina/NWC303-Advance-Routing.git) |
| PRW301 | Introduction to Research | [View Repository](https://github.com/ChetanTimsina/PRW301-Introduction-to-Research.git) |

---

## Other Courses (Not Directly Related to IT)

| Course Code | Course Name | Repository |
|------------|-------------|------------|
| EDP101 | Entrepreneurship | [View Repository](https://github.com/ChetanTimsina/EDP101-Entrepreneurship.git) |
| ECD202 | Digital Electronics & Logic Design | [View Repository](https://github.com/ChetanTimsina/ECD202-Digital-Electronics---Logic-Design.git) |
| PHY101 | Engineering Physics | [View Repository](https://github.com/ChetanTimsina/PHY101-Engineering-Physics.git) |

---

# How to Use

1. Click the course repository you want from the table above.
2. On the course repository page, you can:

   - Clone the repository:

```bash
git clone <course-repo-link>
```

   - Or download the ZIP file for offline use.

3. Each course repository is **independent**, so you only need to clone the courses you require.

---

# Notes

- This repository acts as a **catalog of all CST College courses**.
- Contributors should add new courses as **Git submodules** under the correct category.
- Submodule folders redirect users to the actual course repository.

---

# 📌 Adding New Courses (Submodules)

You can learn how to add Git submodules from this guide:

https://youtu.be/gSlXo2iLBro?si=GscEdPzHyS4iueQ5&t=216

## 🚀 Quick Steps to Add a Course

### 1. Create a New Repository

- Create a separate GitHub repository for the course.
- Organize lectures, notes, assignments, and resources properly.

---

### 2. Clone CST Lectures Repository

```bash
git clone https://github.com/ChetanTimsina/CST_Lectures.git
```

---

### 3. Add the Course as a Submodule

```bash
git submodule add <your-github-repo-url>
```

Example:

```bash
git submodule add https://github.com/username/course-name.git
```

---

### 4. Stage and Commit Changes

```bash
git add .
git commit -m "Added <course-name> as submodule"
```

---

### 5. Push Changes to GitHub

```bash
git push origin main
```

---

💡 **Tip:** Keep your course repository organized and structured so students can easily explore and use the resources.

---

Made with ❤️ by **Chetan Timsina**
