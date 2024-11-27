INSERT INTO module (id, module, title, icon, description)
VALUES
    (1, 'notes', 'Notes', 'mdi-note-outline', 'Record your thoughts, ideas, and important stuff'),
    (2, 'shopping-list', 'Shopping list', 'mdi-cart-outline', 'Organize shopping lists to remember everything'),
    (3, 'fees', 'Fees', 'mdi-cash-multiple', 'Manage your bills and keep track of payments'),
    (4, 'birthdays', 'Birthdays', 'mdi-calendar-heart', 'Record your loved ones\' birthdays and receive reminders'),
    (5, 'recommendations', 'Recommendations', 'mdi-thumb-up-outline', 'Store recommendations from friends'),
    (6, 'class-schedule', 'Lesson plan', 'mdi-school-outline', 'Have your own or your loved ones\' lesson plan at hand'),
    (7, 'meds', 'Meds', 'mdi-pill', 'Track your medication schedule and set reminders'),
    (8, 'codes', 'Codes', 'mdi-qrcode', 'Store access or promotion barcodes and QR codes'),
    (9, 'rates', 'Rate', 'mdi-star', 'Store your personal ratings'),
    (10, 'shared-bill', 'Shared bill', 'mdi-receipt', 'Easily track group expenses with your friends')
ON DUPLICATE KEY UPDATE
    module = VALUES(module),
    title = VALUES(title),
    icon = VALUES(icon),
    description = VALUES(description);
