# 2048

## Compiling Java files using Eclipse IDE

1. Download this repository as ZIP
2. Create new `Java Project` in `Eclipse`
3. Right-click on your `Java Project` --> `Import`
4. Choose `General` --> `Archive File`
5. Put directory where you downloaded ZIP in `From archive file`
6. Put `ProjectName/src` in `Into folder`
7. Click `Finish`

### Linking the UI Library

8. Right-click on your `Java Project` --> `Build Path` --> `Add External Archives`
9. Select `ecs100.jar` and link it to the project. That JAR will be in the directory where you downloaded ZIP

## Running the program

1. Right-click on your `Java Project` --> `Run As` --> `Java Application` --> `Game2048withUndo`

## Build an executable using IntelliJ IDEA

1. Go to **File** → **Project Structure** → **Artifacts**.
2. Click the green plus (**+**) button, select **JAR**, and choose **From modules with dependencies...**
3. In the **Main Class** field, click the folder icon and select the application's entry point class.
4. Under **JAR files from libraries**, select **extract to the target JAR** (this creates the single Fat JAR).
5. Click **OK**, then click **Apply**.
6. From the top menu bar, go to **Build** → **Build Artifacts...** and click **Build**.
7. The executable jar file will be generated inside the project directory under `out/artifacts/`.

### Run the executable JAR file using the command line:

```bash
java -jar path/to/executable.jar
```

## Live Demo

You can run this application directly in your web browser via the link below:

**[Launch Live Demo](https://rjperez94.github.io/2048/)**

## Notes

<strong> Click on the graphics pane first to activate control using arrow keys. </strong>

## Controls

`Left/Right/Up/Down`
<br> `SPACE` to restart
<br> `U` to undo

## Goal

Each time 2 tiles with the same number touch, the numbers are added and the two tiles merge. Produce the magic number of 2048.
