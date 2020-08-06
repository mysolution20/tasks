echo.
echo run crud.bat file
echo.

call  runcrud.bat
if "%ERRORLEVEL%" == "0" goto open_chrome
goto fail
echo.

:open_chrome
echo Opens a chrome browser with given pages
echo.

start chrome --new-window "http://localhost:8080/crud/v1/task/getTasks"   "file://C:/MAMP/htdocs/tasks_frontend/index.html"  "https://kodilla.com/pl/bootcamp-dashboard"
if "%ERRORLEVEL%" == "0" goto :end
goto fail
echo.

: fail
echo  There were errors, cannot run crud.bat file

:end
echo Opening chrome is finished.
