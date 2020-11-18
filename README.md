# rick-and-morty-android-app

## Planned functionality
1. Rewrite `ViewHolder` and `Adapter` to smarter versions :heavy_check_mark:
2. Working with data locally after everything is downloaded (no need to call API all the time, as there is just nearly 700 items) 
2. Set searchbar filter and `AsyncTask` `cancel()` at text change in searchbar :heavy_check_mark:
3. Screen rotation OFF (to get rid of `AsyncTask` memory leaks problems) :heavy_check_mark:
4. `onDestroy()` cancels `AsyncTask` (memory management again) :heavy_check_mark:
5. Smart data binding to `RecyclerView`
   * Bind data to adapter in waves - after 20 items are parsed by API wrapper library, not at once after everything is parsed :heavy_check_mark:
   * Run the app and don't wait until introductory data loading is finished :heavy_check_mark:
   * `Loader` when loading :x: (simulated by bottom red TextView on MainActivity) :heavy_check_mark:
6. Download and show image in `CharacterViewHolder` :heavy_check_mark:
7. Better implementation of `onDestroy()`
   * Support screen rotation
   * Application saves states between rotations
8. Finish filter buttons functionality (now they just change colors)
9. Everything else non-algorithmical, non-low-level aware
   * `ViewHolder onClickListener` with intent to detailed description activity
   * better and nicer UI
   * different resolution screens compatibility
   * ...
