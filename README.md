# rick-and-morty-android-app

## Planned functionality
1. Rewrite `ViewHolder` and `Adapter` to smarter versions :heavy_check_mark:
2. Working with data locally after everything is downloaded (no need to call API all the time, as there is just nearly 700 items)
2. Set searchbar filter and `AsyncTask` `cancel()` at text change in searchbar
3. Screen rotation OFF (to get rid of `AsyncTask` memory leaks problems)
4. `onDestroy()` cancels `AsyncTask` (memory management again)
5. Bind data to adapter in waves - after 20 items are parsed by parsing library, not at once after everything is parsed. Run the app and don't wait until introductory data loading + `Loader` when loading
6. Download and show image in `CharacterViewHolder`

7. Everything else non-algorithmical, non-low-level aware
   * `ViewHolder onClickListener` with intent to detailed description activity
   * better and nicer UI
   * different resolution screens compatibility
   * ...
