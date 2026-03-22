# CST Lectures 

Welcome to **CST Lectures** — a central hub for all courses offered by CST College.  
Each course is in its own repository. Click any course to view, clone, or download it individually.  

---

##  Semester 1 Courses

| Course Code | Course Name | Repository |
|------------|-------------|------------|
| CPL101-PY | Introduction To Programming ( Python ) | [View Repository](https://github.com/ChetanTimsina/CPL101-Introduction-To-Programming-Python-.git) |
| CPL101-C  | Introduction To Programming ( C ) | [View Repository](https://github.com/ChetanTimsina/CPL101-Introduction-To-Programming-C-Program-.git) |
| CPL102-JAVA | Object-Oriented Programming ( Java ) | [View Repository](https://github.com/ChetanTimsina/CPL102-Object-Oriented-Programming-Java-.git) |
| CTE208-FLUTTER | Mobile Application Development ( Flutter ) | [View Repository](https://github.com/ChetanTimsina/CTE208-Mobile-Application-Developement-Flutter-.git) |
| CTE309-ML | Machine Learning AI Model Training | [View Repository](https://github.com/ChetanTimsina/CTE309-Machine-Learning-AI-Model-Training-.git) |
| ITM301 | Professional Practices in IT | [View Repository](https://github.com/ChetanTimsina/ITM301-Professional-Practices-in-IT.git) |
| ITM302-LINUX | System Administration ( Linux ) | [View Repository](https://github.com/ChetanTimsina/ITM302-System-Administration-Linux-.git) |
| MINI-PROJECT | MiniProject | [View Repository](https://github.com/ChetanTimsina/MiniProject.git) |

---

##  Other Courses (Not Directly Related to IT)

| Course Code | Course Name | Repository |
|------------|-------------|------------|
| EDP101 | Entrepreneurship | [View Repository](https://github.com/ChetanTimsina/EDP101-Entrepreneurship.git) |

---

##  How to Use

1. Click the course repository you want from the table above.  
2. On the course repo page, you can:  
   - Clone the repo:  
     ```bash
     git clone <course-repo-link>
     ```  
   - Download ZIP for offline use.  
3. Each course is **independent**, so you only clone what you need.  

---

##  Notes

- This repo acts as a **catalog of all CST College courses**.  
- Contributors: please add new courses as **submodules** in the correct semester folder.  
- Submodule folders on GitHub redirect to the actual course repo.  

---

## 📌 Adding New Courses (Submodules)

You can learn how to add submodules from this YouTube guide:  
https://youtu.be/gSlXo2iLBro?si=GscEdPzHyS4iueQ5&t=216  

### 🚀 Quick Steps to Add a Course

1. **Create a New Repository**  
   - Create a separate GitHub repository for the course.  
   - Organize all lectures, notes, and materials in a clean and structured way.  

2. **Clone the Main CST Lectures Repository**  
   git clone https://github.com/ChetanTimsina/CST_Lectures.git  

3. **Add the Course as a Submodule**  
   git submodule add &lt;your-github-repo-url&gt; 

4. **Stage and Commit Changes**  
   git add .  
   git commit -m "Added &lt;course-name&gt; as submodule"  

5. **Push to GitHub**  
   git push origin main  

💡 Tip: Keep your course repository well-organized so it’s easy for others to explore and use.

---

Made with ❤️ by Chetan Timsina
