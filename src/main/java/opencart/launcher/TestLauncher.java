package opencart.launcher;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class TestLauncher extends JFrame {

    private final List<TestItem> tests = new ArrayList<>();
    private final JPanel testsPanel = new JPanel();
    private final JTextArea outputArea = new JTextArea();
    private final JButton runButton = new JButton("▶ Run Selected");
    private final JButton exportButton = new JButton("Export Log");
    private final JCheckBox chromeCheckBox = new JCheckBox("Chrome", true);
    private final JCheckBox firefoxCheckBox = new JCheckBox("Firefox");
    private final JCheckBox edgeCheckBox = new JCheckBox("Edge");
    private final JCheckBox screenshotsCheckBox = new JCheckBox("Screenshots", true);

    public TestLauncher() {
        createTests();
        createWindow();
    }

    private void createTests() {
        tests.add(new TestItem("👤", "Registration",
                new TestTarget("opencart.tests.RegistrationTests"),
                new TestTarget("opencart.tests.RegisterTests", "registerPositiveTest")));

        tests.add(new TestItem("🚫", "Negative Registration",
                new TestTarget("opencart.tests.RegisterTests", "registerNegativeTestDuplicateEmail",
                        "registerNegativePasswordMismatch", "registerNegativeMissingMandatoryFields")));

        tests.add(new TestItem("🔐", "Authentication",
                new TestTarget("opencart.tests.LoginTests"),
                new TestTarget("opencart.tests.UserAuthenticationTest")));

        tests.add(new TestItem("💱", "Currency", new TestTarget("opencart.tests.CurrencyTests")));
        tests.add(new TestItem("🔎", "Search", new TestTarget("opencart.tests.SearchTests")));
        tests.add(new TestItem("📂", "Categories", new TestTarget("opencart.tests.CategoryNavigationTests")));
        tests.add(new TestItem("🔃", "Grid / List / Sorting", new TestTarget("opencart.tests.CategoryDisplayAndSortTests")));
        tests.add(new TestItem("🖼", "Product Details"));
        tests.add(new TestItem("⚙", "Product Options", new TestTarget("opencart.tests.CustomizableProductOptionsTest")));
        tests.add(new TestItem("⭐", "Product Reviews", new TestTarget("opencart.tests.ProductReviewTest")));
        tests.add(new TestItem("⚖", "Product Comparison", new TestTarget("opencart.tests.ProductComparisonTests")));
        tests.add(new TestItem("♥", "Wishlist", new TestTarget("opencart.tests.WishListTests")));
        tests.add(new TestItem("🛒", "Shopping Cart", new TestTarget("opencart.tests.ShoppingCartOperationsTest")));
        tests.add(new TestItem("🎁", "Coupons / Gift Certificates", new TestTarget("opencart.tests.DiscountCodesTest")));
        tests.add(new TestItem("🚚", "Shipping & Taxes", new TestTarget("opencart.tests.CalculatorTect")));
        tests.add(new TestItem("🧾", "Guest Checkout", new TestTarget("opencart.tests.GuestCheckoutTests")));

        tests.add(new TestItem("🔑", "Returning Checkout",
                new TestTarget("opencart.tests.ReturningCustomerCheckoutTests"),
                new TestTarget("opencart.tests.RegisteredCheckoutTests")));

        tests.add(new TestItem("🏠", "Billing / Delivery", new TestTarget("opencart.tests.AddressValidationTests")));
        tests.add(new TestItem("💳", "Shipping / Payment", new TestTarget("opencart.tests.ShippingPaymentTests")));
        tests.add(new TestItem("✅", "Terms & Conditions", new TestTarget("opencart.tests.TermsAndConditionsTest")));
        tests.add(new TestItem("✉", "Contact Us", new TestTarget("opencart.tests.ContactUsTests")));
        tests.add(new TestItem("↩", "Product Returns", new TestTarget("opencart.tests.ReturnRequestTests")));
        tests.add(new TestItem("📜", "Order History / Downloads", new TestTarget("opencart.tests.AccountHistoryTests")));
        tests.add(new TestItem("🖥", "Responsive / Cross-browser"));
    }

    private void createWindow() {
        setTitle("OpenCart Automation Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 820);
        setMinimumSize(new Dimension(800, 700));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(15, 15));
        root.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("OpenCart Automation Launcher");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel subtitle = new JLabel("Select automated tests, browsers and start execution");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        JPanel browserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        browserPanel.setBorder(BorderFactory.createTitledBorder("Browsers"));
        browserPanel.add(chromeCheckBox);
        browserPanel.add(firefoxCheckBox);
        browserPanel.add(edgeCheckBox);
        browserPanel.add(Box.createHorizontalStrut(15));
        browserPanel.add(screenshotsCheckBox);

        testsPanel.setLayout(new GridLayout(0, 2, 10, 8));

        for (TestItem test : tests) {
            test.checkBox = new JCheckBox(test.icon + "  " + test.name);
            test.checkBox.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
            test.checkBox.setFocusPainted(false);

            if (test.targets.isEmpty()) {
                test.checkBox.setEnabled(false);
                test.checkBox.setToolTipText("No automated test connected");
            }

            testsPanel.add(test.checkBox);
        }

        JScrollPane testsScroll = new JScrollPane(testsPanel);
        testsScroll.setBorder(BorderFactory.createTitledBorder("Automation Tests"));
        testsScroll.getVerticalScrollBar().setUnitIncrement(16);

        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.add(browserPanel, BorderLayout.NORTH);
        center.add(testsScroll, BorderLayout.CENTER);

        JButton selectAllButton = new JButton("Select All");
        JButton clearButton = new JButton("Clear");

        selectAllButton.addActionListener(e -> tests.stream()
                .filter(test -> test.checkBox.isEnabled())
                .forEach(test -> test.checkBox.setSelected(true)));

        clearButton.addActionListener(e -> tests.forEach(test -> test.checkBox.setSelected(false)));
        runButton.addActionListener(e -> runSelectedTests());
        exportButton.addActionListener(e -> exportLog());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttons.add(runButton);
        buttons.add(selectAllButton);
        buttons.add(clearButton);
        buttons.add(exportButton);

        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        outputArea.setRows(11);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Execution Log"));

        JPanel bottom = new JPanel(new BorderLayout(10, 10));
        bottom.add(buttons, BorderLayout.NORTH);
        bottom.add(outputScroll, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        root.add(bottom, BorderLayout.SOUTH);
        setContentPane(root);
    }

    private void runSelectedTests() {
        List<TestItem> selectedTests = tests.stream().filter(test -> test.checkBox.isSelected()).toList();
        List<String> selectedBrowsers = getSelectedBrowsers();

        if (selectedTests.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select at least one test.", "OpenCart Automation", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (selectedBrowsers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select at least one browser.", "OpenCart Automation", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        runButton.setEnabled(false);
        outputArea.setText("");
        boolean screenshotsEnabled = screenshotsCheckBox.isSelected();

        SwingWorker<Void, Void> worker = new SwingWorker<>() {

            @Override
            protected Void doInBackground() {
                PrintStream originalOut = System.out;
                PrintStream originalErr = System.err;

                try {
                    PrintStream launcherStream = new PrintStream(
                            new LauncherOutputStream(originalOut, TestLauncher.this::appendOutput), true, StandardCharsets.UTF_8);

                    System.setOut(launcherStream);
                    System.setErr(launcherStream);

                    appendOutput("OpenCart Automation");
                    appendOutput("Browsers: " + String.join(", ", selectedBrowsers));
                    appendOutput("Screenshots: " + (screenshotsEnabled ? "enabled" : "disabled"));
                    appendOutput("");

                    int totalPassed = 0;
                    int totalFailed = 0;
                    int totalSkipped = 0;

                    for (String browser : selectedBrowsers) {
                        appendOutput("========================================");
                        appendOutput("Browser: " + browser.toUpperCase());
                        appendOutput("========================================");

                        System.setProperty("browser", browser);
                        XmlSuite suite = createSuite(selectedTests, browser);

                        if (suite == null) {
                            appendOutput("No executable tests for " + browser + ".");
                            continue;
                        }

                        LauncherTestListener listener = new LauncherTestListener(browser, screenshotsEnabled);
                        TestNG testNG = new TestNG();

                        testNG.setXmlSuites(List.of(suite));
                        testNG.setOutputDirectory("build/test-output/" + browser + "/" + timestamp());
                        testNG.setVerbose(1);
                        testNG.addListener(listener);
                        testNG.run();

                        totalPassed += listener.getPassed();
                        totalFailed += listener.getFailed();
                        totalSkipped += listener.getSkipped();

                        appendOutput("Browser summary: " + listener.getPassed() + " passed, "
                                + listener.getFailed() + " failed, " + listener.getSkipped() + " skipped.");
                        appendOutput("");
                    }

                    appendOutput("========================================");
                    appendOutput("TOTAL: " + totalPassed + " passed, " + totalFailed + " failed, " + totalSkipped + " skipped.");
                    appendOutput("========================================");
                } catch (Exception e) {
                    appendOutput("Launcher error: " + e.getClass().getSimpleName() + ": " + safeMessage(e));
                } finally {
                    System.setOut(originalOut);
                    System.setErr(originalErr);
                }

                return null;
            }

            @Override
            protected void done() {
                runButton.setEnabled(true);
                saveAutomaticLog();
            }
        };

        worker.execute();
    }

    private XmlSuite createSuite(List<TestItem> selectedTests, String browser) {
        Map<String, MethodSelection> selections = new LinkedHashMap<>();

        for (TestItem item : selectedTests) {
            for (TestTarget target : item.targets) {
                MethodSelection selection = selections.computeIfAbsent(target.className, key -> new MethodSelection());

                if (target.methods.isEmpty()) {
                    selection.allMethods = true;
                    selection.methods.clear();
                } else if (!selection.allMethods) {
                    selection.methods.addAll(target.methods);
                }
            }
        }

        XmlSuite suite = new XmlSuite();
        suite.setName("OpenCart Automation - " + browser);

        XmlTest test = new XmlTest(suite);
        test.setName("Selected Tests - " + browser);

        List<XmlClass> xmlClasses = new ArrayList<>();

        for (Map.Entry<String, MethodSelection> entry : selections.entrySet()) {
            try {
                Class.forName(entry.getKey());
            } catch (ClassNotFoundException e) {
                appendOutput("Class not found: " + entry.getKey());
                continue;
            }

            XmlClass xmlClass = new XmlClass(entry.getKey());
            MethodSelection selection = entry.getValue();

            if (!selection.allMethods && !selection.methods.isEmpty()) {
                xmlClass.setIncludedMethods(selection.methods.stream().map(XmlInclude::new).toList());
            }

            xmlClasses.add(xmlClass);
        }

        if (xmlClasses.isEmpty()) return null;

        test.setXmlClasses(xmlClasses);
        return suite;
    }

    private List<String> getSelectedBrowsers() {
        List<String> browsers = new ArrayList<>();

        if (chromeCheckBox.isSelected()) browsers.add("chrome");
        if (firefoxCheckBox.isSelected()) browsers.add("firefox");
        if (edgeCheckBox.isSelected()) browsers.add("edge");

        return browsers;
    }

    private void appendOutput(String text) {
        Runnable append = () -> {
            outputArea.append(text + System.lineSeparator());
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        };

        if (SwingUtilities.isEventDispatchThread()) append.run();
        else SwingUtilities.invokeLater(append);
    }

    private void exportLog() {
        if (outputArea.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "There is no log to export.", "OpenCart Automation", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("opencart-test-log-" + timestamp() + ".txt"));

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        try {
            Files.writeString(chooser.getSelectedFile().toPath(), outputArea.getText(), StandardCharsets.UTF_8);
            JOptionPane.showMessageDialog(this, "Log exported successfully.", "OpenCart Automation", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Could not export log: " + safeMessage(e),
                    "OpenCart Automation", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAutomaticLog() {
        try {
            Path directory = Path.of("build", "launcher-logs");
            Files.createDirectories(directory);

            Path file = directory.resolve("opencart-test-log-" + timestamp() + ".txt");
            Files.writeString(file, outputArea.getText(), StandardCharsets.UTF_8);
            appendOutput("Log saved: " + file.toAbsolutePath());
        } catch (IOException e) {
            appendOutput("Could not save automatic log: " + safeMessage(e));
        }
    }

    private static String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS"));
    }

    private static String safeMessage(Throwable throwable) {
        if (throwable == null) return "";
        String message = throwable.getMessage();
        return message == null || message.isBlank() ? throwable.toString() : message;
    }

    private class LauncherTestListener implements ITestListener, IInvokedMethodListener {

        private final String browser;
        private final boolean screenshotsEnabled;
        private int passed;
        private int failed;
        private int skipped;

        private LauncherTestListener(String browser, boolean screenshotsEnabled) {
            this.browser = browser;
            this.screenshotsEnabled = screenshotsEnabled;
        }

        @Override
        public void beforeInvocation(IInvokedMethod method, ITestResult result) {
            if (method.isTestMethod()) {
                appendOutput("▶ " + result.getTestClass().getRealClass().getSimpleName() + "."
                        + result.getMethod().getMethodName());
            }
        }

        @Override
        public void afterInvocation(IInvokedMethod method, ITestResult result) {
            if (method.isTestMethod() && screenshotsEnabled) takeScreenshot(result);
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            passed++;
            appendOutput("✓ " + getTestName(result));
        }

        @Override
        public void onTestFailure(ITestResult result) {
            failed++;
            appendOutput("✖ " + getTestName(result) + " — " + safeMessage(result.getThrowable()));
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            skipped++;
            appendOutput("○ " + getTestName(result));
        }

        private String getTestName(ITestResult result) {
            return result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
        }

        private void takeScreenshot(ITestResult result) {
            WebDriver driver = findDriver(result.getInstance());

            if (!(driver instanceof TakesScreenshot screenshotDriver)) {
                appendOutput("Screenshot unavailable for " + result.getMethod().getMethodName());
                return;
            }

            try {
                String status = switch (result.getStatus()) {
                    case ITestResult.SUCCESS -> "passed";
                    case ITestResult.FAILURE -> "failed";
                    case ITestResult.SKIP -> "skipped";
                    default -> "finished";
                };

                Path directory = Path.of("build", "screenshots", browser);
                Files.createDirectories(directory);

                String fileName = result.getTestClass().getRealClass().getSimpleName() + "_"
                        + result.getMethod().getMethodName() + "_" + status + "_" + timestamp() + ".png";

                File source = screenshotDriver.getScreenshotAs(OutputType.FILE);
                Path destination = directory.resolve(fileName);

                Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                appendOutput("Screenshot: " + destination.toAbsolutePath());
            } catch (Exception e) {
                appendOutput("Screenshot error: " + safeMessage(e));
            }
        }

        private WebDriver findDriver(Object instance) {
            if (instance == null) return null;

            Class<?> type = instance.getClass();

            while (type != null) {
                try {
                    Field field = type.getDeclaredField("driver");
                    field.setAccessible(true);
                    Object value = field.get(instance);
                    if (value instanceof WebDriver webDriver) return webDriver;
                } catch (NoSuchFieldException ignored) {
                } catch (IllegalAccessException e) {
                    return null;
                }

                type = type.getSuperclass();
            }

            return null;
        }

        public int getPassed() {
            return passed;
        }

        public int getFailed() {
            return failed;
        }

        public int getSkipped() {
            return skipped;
        }
    }

    private static class LauncherOutputStream extends OutputStream {

        private final PrintStream original;
        private final Consumer<String> consumer;
        private final StringBuilder buffer = new StringBuilder();

        private LauncherOutputStream(PrintStream original, Consumer<String> consumer) {
            this.original = original;
            this.consumer = consumer;
        }

        @Override
        public synchronized void write(int b) {
            original.write(b);
            processCharacter((char) b);
        }

        @Override
        public synchronized void write(byte[] bytes, int offset, int length) {
            original.write(bytes, offset, length);
            String text = new String(bytes, offset, length, StandardCharsets.UTF_8);

            for (char character : text.toCharArray()) processCharacter(character);
        }

        private void processCharacter(char character) {
            if (character == '\n') emit();
            else if (character != '\r') buffer.append(character);
        }

        private void emit() {
            if (buffer.length() == 0) return;
            consumer.accept(buffer.toString());
            buffer.setLength(0);
        }

        @Override
        public synchronized void flush() {
            original.flush();
            emit();
        }
    }

    private static class TestItem {

        private final String icon;
        private final String name;
        private final List<TestTarget> targets;
        private JCheckBox checkBox;

        private TestItem(String icon, String name, TestTarget... targets) {
            this.icon = icon;
            this.name = name;
            this.targets = List.of(targets);
        }
    }

    private static class TestTarget {

        private final String className;
        private final List<String> methods;

        private TestTarget(String className, String... methods) {
            this.className = className;
            this.methods = List.of(methods);
        }
    }

    private static class MethodSelection {
        private boolean allMethods;
        private final Set<String> methods = new LinkedHashSet<>();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestLauncher().setVisible(true));
    }
}