Android-Activity-Flags
======================

Small app to showcase activity navigation


**ScenarioC** actually showcases poorly documented feature of SingleTask flag for activity tag in manifest files.<br>
When launched, activity should look for its instances throughout other task. However, it skips task with the same [affinity](https://stackoverflow.com/a/17873524/2673127]). Be careful about this detail when you use it.
