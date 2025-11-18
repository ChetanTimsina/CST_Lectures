import os
import comtypes.client

# --- CONFIG ---
folder_path = r"C:\Users\User\OneDrive\Desktop\System Administration"  # change to your folder

# initialize PowerPoint
ppt_app = comtypes.client.CreateObject("PowerPoint.Application")
ppt_app.Visible = 1

# loop through all files
for filename in os.listdir(folder_path):
    if filename.lower().endswith((".ppt", ".pptx")):
        full_path = os.path.join(folder_path, filename)
        pdf_path = os.path.join(folder_path, os.path.splitext(filename)[0] + ".pdf")
        
        print(f"Converting {filename} → {os.path.basename(pdf_path)}")
        
        presentation = ppt_app.Presentations.Open(full_path, WithWindow=False)
        presentation.SaveAs(pdf_path, FileFormat=32)  # 32 = PDF
        presentation.Close()

ppt_app.Quit()
print("All PPT/PPTX files converted to PDF!")
