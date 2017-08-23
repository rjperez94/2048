# 2048

## Compiling Java files using Eclipse IDE

1. Download this repository as ZIP
2. Create new `Java Project` in `Eclipse`
3. Right click on your `Java Project` --> `Import`
4. Choose `General` --> `Archive File`
5. Put directory where you downloaded ZIP in `From archive file`
6. Put `ProjectName/src` in `Into folder`
7. Click `Finish`

### Linking the UI Library

8. Right click on your `Java Project` --> `Build Path` --> `Add External Archives`
9. Select `ecs100.jar` and link it to the project. That JAR will be in the directory where you downloaded ZIP

## Running the program

1. Right click on your `Java Project` --> `Run As` --> `Java Application` --> `Game2048withUndo`

## Notes

<strong> Click on the graphics pane first to activate control using arrow keys. </strong>

## Controls

`Left/Right/Up/Down`
<br> `SPACE` to restart
<br> `U` to undo

## Goal

Each time 2 tiles with the same number touch, the numbers are added and the two tiles merge. Produce the magic number of 2048.
