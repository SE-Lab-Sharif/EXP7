# آزمایش هفتم

در ابتدا می‌بینیم که کلاس CodeGenerator توابع زیادی را expose کرده است و کلاس Parser که از این کلاس استفاده می‌کند از تعداد خیلی کمی از این توابع استفاده می‌کند. برای همین یک شی از نوع CodeGeneratorFacade می‌سازیم که فانکشنالیتی‌های لازم را برای کلاس Parser محیا می‌کند.

![image](https://github.com/user-attachments/assets/27c72ac3-588a-46a9-9eb8-649916f85fe0)

از این کلاس CodeGeneratorFacade در کلاس Parser استفاده می‌کنیم. همچنین مشاهده می‌کنیم که در کلاس SymbolTable از کلاس Memory استفاده شده است، اما از میان متدهای متعددی که این کلاس ارائه می‌دهد، تنها یک متد مورد استفاده قرار می‌گیرد. بر این اساس، یک کلاس MemoryFacade نیز برای استفاده در کلاس SymbolTable طراحی می‌کنیم تا دسترسی به قابلیت‌های مورد نیاز را ساده‌تر کند.
![image](https://github.com/user-attachments/assets/49745676-b84c-4a86-a32c-3dd994e4faf2)
پس از پیاده‌سازی دو الگوی Facade، اکنون به پیاده‌سازی یک کلاس با الگوی Strategy می‌پردازیم. در کلاس SymbolTable، یک switch-statement وجود دارد که رفتارهای مختلف کلاس Action را پیاده‌سازی می‌کند. این ساختار switch را می‌توان با استفاده از الگوی طراحی Strategy بهینه‌سازی کرد. برای این منظور، کلاس Action را به یک کلاس abstract تبدیل می‌کنیم و enum act را حذف می‌کنیم. به جای این ساختار، سه پیاده‌سازی مختلف از کلاس Action خواهیم داشت که هر کدام یکی از حالت‌های switch قبلی را پوشش می‌دهند.
![image](https://github.com/user-attachments/assets/3496bf38-7f2e-4492-a65c-f76ecbd5c713)

سه نوع Action به نام‌های AcceptAction، ReduceAction و ShiftAction داریم که هر کدام عملیات مربوط به خود را در متد act (که در کلاس پدر تعریف شده است) پیاده‌سازی می‌کنند. همچنین، هر یک از این سه کلاس، پیاده‌سازی مخصوص به خود را برای متد ToString دارند. با استفاده از این الگوی طراحی، می‌توانیم کل ساختار پیچیده‌ی switch قبلی را به صورت ساده‌تر و منعطف‌تری پیاده‌سازی کنیم.
currentAction.act(this);

در ادامه، اصل "جداسازی کوئری از تغییردهنده" (Separate Query from Modifier) را پیاده‌سازی می‌کنیم. در کلاس Memory، دو متد getter به نام‌های getTemp و getDataAddress وجود دارند که علاوه بر بازیابی مقادیر، عملیات به‌روزرسانی داده‌ها را نیز انجام می‌دهند. برای بهبود طراحی کد، دو متد جدید به نام‌های incrementTemp و incrementDataAddress اضافه می‌کنیم. این متدها به طور مجزا و تنها در زمان نیاز به افزایش این مقادیر فراخوانی می‌شوند.
![image](https://github.com/user-attachments/assets/ad2e9c98-6c13-4636-adb3-da068149ef2c)




## سوالات

### سوال ۱
هر یک از مفاهیم زیر را به ترتیب توضیح می‌دهیم:

<ul>
  <li> کد تمیز: کدی تمیز است که به سادگی قابل خواندن، قابل درک و قابل نگهداری (maintain) باشد. گرچه تعریف کتاب clean code از uncle bob بسیار ساده‌تر است!‌ "کدی تمیز است که بتوان آنرا به سادگی فهمید. زیرا قابل درک بودن، این ویژگی‌ها را نتیجه می‌دهد: خوانابودن، تغییرپدیری،‌توانایی توسعه پدیری و توانایی نگهداری "</li>
  <a href="https://gist.github.com/wojteklu/73c6914cc446146b8b533c0988cf8d29"> a summary of clean code </a>

  <li> بدهی فنی: بدهی فنی یعنی هزینه‌ی بیشتری که باید بابت کار مجدد بپردازیم، زیرا در پیاده‌سازی آن بخش، قبل‌تر، مسیرهای ساده‌تر و سریع‌تر را به مسیرهای بهتر و long-term ولی با زمان پیاده‌سازی بیشتر برتری داده بودیم. درواقع، هزینه‌ی اضافه‌ی تحمیل شده به ما برای تغییر و یا extend کردن کد، بخاطر اینکه قبلا در پیاده‌سازیمان shortcut های ساده و سریع را به راه‌های تمیزتر و دقیق‌تر ولی با زمان بیشتر برتری داده بودیم، technical debt است.</li>
  <a href="https://www.productplan.com/glossary/technical-debt/"> source </a>
  <li> بوی بد: code smell به بخشی از قطعه‌ای کد گفته می‌شود که نشانگر مشکلات عمیق‌تر و جدی‌تر (شامل flaw در طراحی و یا معماری آن کد) می باشد، هرچند آن قطعه کد به درستی کار کند. درواقع bad smell پتانسیل‌ آن کد در malfunction در شرایط متفاوتی را بیان می‌کند که سبب مشکلات عمیق‌تری می‌شوند (مثلا عدم توانایی در extend کردن سیستم بعدها، و یا پایین آوردن maintainability و ...). این مشکلات می‌توانند در نهایت باعث کیفیت پایین کد و technical debt شوند.</li>
  <a href="https://www.opsera.io/blog/what-is-code-smell#:~:text=Code%20Smells%20are%20the%20traces,code%20quality%20and%20technical%20debt."> source </a>
  </ul>
  
  ### سوال ۲
  دسته‌بندی‌های refactoring guru را از code smell توضیح‌ می‌دهیم:
  <ul>
    <li> نوع bloaters: این نوع (bloaters) از code smell به زمانی گفته می‌شود که قطعه کدی، کلاسی و یا متدی آنقدر بزرگ و حجیم شده باشد که کار کردن با آن دشوار باشد. این نوع از code smell معمولا به یکباره بوجود نمی‌آید بلکه با گذر زمان، کم‌کم به حجم آن اضافه می‌شود و درنهایت maintainability و extensibility و readability را کم می‌کند.
    </li>
    <li>
    نوع Object Oriented Abusers: این نوع از code smell درنتیجه‌ی نفهمیدن درست یا اشتباه در استفاده کردن از اصول و قواعد object oriented programming نشئت می‌گیرند. (مثلا inheritance یا polymorphism یا ...) و درنهایت باعث طراحی inefficient و low quality می‌شوند.
    </li>
    <li>
    نوع Change Preventers: این نوع از code smell زمانی اتفاق می‌افتد که وقتی بخواهیم تغییری در کد ایجاد کنیم، نیاز باشد تا تغییرات زیادی در بخش‌های دیگری از کد نیز اعمال کنیم. درواقع، تغییرپذیری کد و extensibility آن کاهش می‌یابد.
    </li>
    <li>
    نوع Dispensables: این نوع از code smell زمانی اتفاق می‌افتد که قطعه‌ای از کد، کلاس یا متدی وجود داشته باشد که اصلا استفاده نمی‌شود. این نوع از code smell باعث افزایش complexity و کاهش readability می‌شود. درواقع با حذف این قطعه‌های اضافه (dispensable) به خوانایی و تمیزی کد کمک می‌کنیم.
    </li>
    <li>
    نوع Couplers: این نوع از code smell زمانی اتفاق می‌افتد که قطعه‌ای از کد، کلاس یا متدی وجود داشته باشد که به طور مستقیم به دیگر قطعه‌های کد متصل شده باشد. این نوع از code smell باعث افزایش coupling و کاهش cohesion می‌شود. این یعنی componentهای ما بیش از حد به هم وابسته هستند و این، تست و develop و extend کد را با سختی زیادی مواجه می‌کند.
    </li>
  </ul>

  <a href="https://refactoring.guru/refactorings/smells"> source </a>

  ### سوال ۳
  در مورد code smell با عنوان Lazy Class توضیحات زیر را می‌دهیم:

  <ol>
    <li> این نوع از code smell در دسته‌ی Dispensables قرار می‌گیرد. درواقع تعریف آن این است که "اگر کلاسی به اندازه‌ی کافی کار و operation انجام نمی‌دهد که توجه شما را به خود جلب کند، آنرا حذف کنید! زیرا نگهداری و توسعه‌ی class ها زمان و انرژی می‌برد."</li>
    <li> برای component هایی که تقریبا useless هستند، باید از بازآرایی Inline Class استفاده کنیم. برای subclass هایی با توابع کم،‌ از بازآرایی Collapse Hierarchy استفاده می‌کنیم.</li>
    <li> گاهی اوقات یک lazy class در اثر تعیین کردن اهداف development در آینده ایجاد می‌شود. باید حواسمان به این trade-off باشد و یک بالانس نسبی بین clarity و simplicity در کد برقرار کنیم و مراقب باشیم که با حدف این lazu class ها، future development ما کند نشود. (در این شرایط معمولا حذف lazy class کار صحیحی نیست. چون بعدا قرار است از این حالت lazy بودن خارج شود.)</li>
  </ol>

  ### سوال ۴
  مورد اول: Large Class

به دایرکتوری زیر توجه کنید: 
```
src/com/project/phase1CodeGeneration/Phase1CodeGenerator.java
```
در این فایل یک مثال واضح از Long Class می‌بینیم. ابتدای این فایل بصورت زیر است:
```java
public class Phase1CodeGenerator {

    boolean successFull;

    /// TODO move to CompleteDiagram
    public Phase1CodeGenerator(CompleteDiagram diagram)
    {
        successFull = false;
        ....

```
همانطور که مشاهده می‌کنید، این کلاس بیش از 330 خط است که نشان‌دهنده‌ی code smell با عنوان large class است. البته که این تنها مثال در کل این code base نیست و مثال‌های بیشتری از large class نیز در آن دیده می‌شود.

مورد دوم: Long Method

به دایرکتوری زیر توجه کنید:
```
src/com/project/lexicalAnalyzer/LexicalAnalyzer.java
```

در این فایل یک اینترفیس `lexicalAnalyzer` تعریف شده است که در آن تابع زیر وجود دارد:
```java
static Vector<Pair<TokenTypes, String>> getTokensOfPhase2Files(String fileName) {
  ....
    }
```
این تابع، طولی بیش از حدود 250 خط دارد!‌ که بدیهتا این طول زیاد برای یک تابع، نشان‌دهنده‌ی code smell با عنوان long method است و نگهداری و extendibility را بشدت کاهش می‌دهد.

مورد سوم: Feature Envy

به دایرکتوری زیر توجه کنید:
```
src/com/project/phase2CodeGeneration/Phase2CodeGenerator.java
```
که در آن کلاس زیر وجود دارد:
```java
public class Phase2CodeGenerator
```
در این کلاس، بیشتر از اینکه خود کلاس دارای property هایی باشد و به آنها دسترسی پیدا کند، به ویژگی‌های کلاس‌های دیگر دسترسی پیدا می‌کند. البته این مصداق در این قسمت کم است و شاید حتی قابل چشم‌پوشی باشد ولی همچنان اعمال refactoring هایی مثل MoveMethod و یا ExtractMethod می‌تواند آنرا بهبود دهد. درجاهای دیگری از این repository نیز envy های ریز و درشت متفاوت در جاهای مختلف و کلاس‌های مختلف می‌بینیم.

مورد چهارم: Temporary Field

به دایرکتوری زیر توجه کنید:
```
src/com/project/phase2CodeGeneration/ClassInfo.java
```
و یا این دایرکتوری:
```
src/com/project/classBaseUML/ClassStructure.java
```
در هر دوی این دایرکتوری‌ها، کلاس‌های ما دارای فیلدهایی هستند که بسیاری از اوقات می‌توانند null باشند و در برخی اوقات مقدار می‌گیرند. برای مثال، فیلد `superClass` در کلاس زیر:

```java
public class ClassStructure<TType extends ValueType, TAttribute extends ClassAttribute<TType>
        , TConstructor extends ClassConstructor<TType, TAttribute>,
        TMethod extends ClassMethod<TType, TAttribute>> implements DescriptiveMember {
    private final Vector<TConstructor> constructors = new Vector<>();
    private final Vector<TAttribute> attributes = new Vector<>();
    private final Vector<TMethod> methods = new Vector<>();
    private boolean havingDestructor = false;
    private String superClass = "null";
    private String name;

    ...

```
و یا فیلد `name` در کلاس در دایرکتوری اول که برخی مواقع null است. مطابق تعریف، این‌ها می‌توانند code smell از نوع temporary field باشند و برای بهبود کیفیت کد باید آنها را حل کرد.
یک feature envy مهم‌تر و واضح‌تر در دایرکتوری زیر است:
```
src/com/project/phase1CodeGeneration/CompleteDiagram.java
```
به متد زیر توجه کنید: 
```java
public String generateClassNamesSeparatedByNewline()
{
    StringBuilder stringBuilder = new StringBuilder();
    for(String className:allClassNames())
        stringBuilder.append(className).append(newLine);
    return stringBuilder.toString();
}
```
اینجا می‌بینید که این متد تماما در حال استفاده از className از کلاس ClassDiagram است و تمامی operation های آن وابسته به این کلاس است (بجز یک string concatenation ساده). پس بدیهی‌است که جابجایی آن به کلاس ClassDiagram می‌تواند ایده‌ی بهتری باشد.

مورد پنجم: Dead Code

به دایرکتوری زیر توجه کنید:
```
src/com/project/phase1CodeGeneration/CompleteDiagram.java
```

قطعه کدهایی در این کلاس هست که comment out شده اند و عملا استفاده نمی‌شوند و اجرا نمی‌شوند. برای مثال، قطعه کد زیر:

```java
    String generateDeleteBody() {
        StringBuilder allLines = new StringBuilder();
        allLines.append(voidKeyword + whiteSpace + mangledDeleteName())
                .append(DescriptiveMember.generateParamsTogether(
                new Vector<>(), CompleteAttribute.generateAttributeThisText(getName())));
        allLines.append(newLine + openCurlyBracket + newLine);
        allLines.append(tab).append(generateDestructorName())
                .append(openParenthesis + thisKeyword + closeParenthesis + semiColon + newLine);
//        allLines.append(tab + freeKeyword + openParenthesis).append(thisKeyword).append(closeParenthesis)
//                .append(semiColon).append(newLine);
        allLines.append(closeCurlyBracket).append(newLine);

        return allLines.toString();
    }
```

و یا در دایرکتوری زیر:
```
src/com/project/phase2CodeGeneration/phase2CodeFileManipulator.java
```
قطعه‌کد زیر را به همین صورت می‌بینیم:
```java
    private void printStack()
    {
        for(CompleteAttribute attribute : this.variableStack.lastElement())
        {
            StringBuilder builder = new StringBuilder();
            builder.append(tab.repeat(Math.max(0,
                    this.depthOfCurlyBracket + (lineState.equals(LineState.RETURN_LINE)? 1:0))));

            if(diagramInfo.isHaveDestructor(attribute.getValueType().getTypeName()))
                builder.append(deleteManipulate);
//            else
//                builder.append(freeKeyword);

            if(diagramInfo.isHaveDestructor(attribute.getValueType().getTypeName()))
                builder.append(openParenthesis).append(and).append(attribute.getName())
                        .append(closeParenthesis).append(semiColon).append(newLine);
            write(builder.toString());
        }
    }
```

در اینجا `buillder.append(freeKeyword);` کامنت شده است و عملا استفاده نمی‌شود. این نوع code smell به نام dead code است و باید حذف شود.

مورد ششم: Data Class

به دایرکتوری زیر توجه کنید:
```
src/com/project/graphBaseDependency/ClassNode.java
```

```java
public class ClassNode {
    private final Vector <DependencyEdge> edges;
    private final String name;
    private boolean visit;
    private int dependencyCycleType;

    ...
```

در این کلاس، فقط فیلدهایی وجود دارد و هیچ متدی برای انجام عملیات روی این فیلدها وجود ندارد. صرفا مجموعه‌ای از getter ها و setter ها را داریم و به خودی خود، عملیات مجزایی روی این فیلدها انجام نمی‌شود. این نوع code smell به نام data class است و برای بهبود کیفیت کد باید این کلاس را refactoring کرد.

مورد هفتم: Speculative Generality

برخی از component های کد در این codebase به وضوح برای استفاده‌ی در آینده و چیزهایی که هنوز implement نشده‌اند، over-engineered شده‌اند و به نوعی implement شده اند که آن تغییرات آینده را نیز ساپورت کنند (که هنوز implement نشده اند!). مثلا این نمونه را ببینید:

```java
// Unnecessarily generic/abstract enums and handlers that are overly prepared for future cases
private enum LineState {
    NEW_LINE,
    POSSIBLY_INITIALIZER,  // Speculative state that's rarely used
    DEFINITELY_NOT_A_INITIALIZER,
    RETURN_LINE
}

private enum LocationState {
    OUTSIDE,
    STRUCTURE,
    METHOD,
    CONSTRUCTOR,
    DESTRUCTOR  // Some of these states appear over-engineered
}
```

و یا مثال زیر را ببینید:
```java
// File: MethodOverloader.java
// Over-generalized for potential future parameter counts
private int maxNumberOfParams;
```
این `maxNumberOfParameters` در هیچ‌جایی از کد، متفاوت از مفهوم `exactNumberOfParameters` استفاده نشده است! این یعنی که این component برای یک احتمال آینده over-engineered شده است. این نوع code smell به نام speculative generality است و برای بهبود کیفیت کد باید این component ها را refactoring کرد.

مورد هشتم: Inappropriate Intimacy

به مثال زیر توجه کنید:
```java
public class Phase2CodeFileManipulator {
    // Too much intimate access to DiagramInfo class internals
    private final DiagramInfo diagramInfo;

    ...

    // Excessive calls to DiagramInfo methods showing tight coupling:
    diagramInfo.isHaveConstructor()
    diagramInfo.isHaveDestructor() 
    diagramInfo.getMethods()
    diagramInfo.getAttributes()
    diagramInfo.getClassNames()
    ...
}
```
در این مثال همانطور که می‌بینید، این کلاس با `DiagramInfo` مقدار زیادی coupled است. همانطور که در کد نیز مشخص است، به ذفعات زیادی و در موارد متنوعی، این کلاس به متدهای internal کلاس `DiagramInfo` دسترسی پیدا می‌کند و به آن وابسنه است. این نوع code smell به نام inappropriate intimacy است و برای بهبود کیفیت کد باید این دو component را refactoring کرد.

مورد نهم: Switching Statements

به دایرکتوری زیر توجه کنید:
```
src/com/project/lexicalAnalyzer/LexicalAnalyzer.java
```
که کد زیر در آن است:
```java
TokenTypes tokenType;
            switch (type) {
                case "AUTO":
                    tokenType = TokenTypes.AUTO;
                    break;
                case "CHAR":
                case "DOUBLE":
                case "FLOAT":
                case "INT":
                case "LONG":
                case "SHORT":
                case "VOID":
                    tokenType = TokenTypes.TYPES;
                    break;
                case "CONST":
                    tokenType = TokenTypes.CONST;
                    break;
                .... 
```
که این switch statement بسیار طولانی است و ادامه دارد!‌ این، نمونه‌ای واضح از Switching Statements code smell است که برای رفع آن باید از ریفکتورینگ‌هایی مثل Strategy یا make a map استفاده کرد.

مورد دهم: Data Clumps

به این مورد توجه کنید:
```java
public class Phase2CodeFileManipulator {
    // Data Clump 1: State-related fields that always appear together
    private int numberOfClassCalled;
    private int numberOfStructureKeywordCalled;
    private int numberOfClassKeywordCalled;
    private int numberOfTypeSpecifierCalled;
    private int numberOfTypeDetailAdded;
    private int numberOfIDCalled;
    
    // Data Clump 2: Depth tracking variables that are used together
    private int depthOfParenthesis;
    private int depthOfCurlyBracket;
    
    // Data Clump 3: Class tracking fields that are used together
    private String firstClassCalled;
    private String lastClassCalled;
    private String locationClass;
}
```

اینجا نمونه هایی واضح از data clump می‌بینیم که آنها را مشخص کرده‌ایم. مثلا، تمامی فیلدهایی که در بالا با عنوان `numberOf*` می‌بینید همه با هم تعریف شده و همه با یکدیگر استفاده می‌شوند (در متدهای این کلاس). پس بهتر است یک کلاس `CounterState` داشته باشیم و این‌ها را در آن کلاس encapsulate کنیم. موارد دیگر نیز در کامنت عنوان شده اند و نمونه‌هایی از code smell با عنوان Data Clump هستند که برای بهبود کیفیت کد باید آنها را refactoring کرد.

### سوال ۵
پلاگین formatter یک پلاگین maven است که براساس Eclipse Code formatter engine است و کمک می‌کند که کد را بر اساس standard های مشخصی format کنیم. در واقع این پلاگین به سادگی به ما کمک می‌کند تا کد جاوای خود را طوری فرمت کنیم که همواره از یک style مشخص پیروی کند. مثلا ساده‌ترین موارد formatting که توسط این پلاگین همگام‌سازی و مدیریت می‌شوند این‌ها هستند:

<ul>
  <li> Indentation </li>
  <li> Line width </li>
  <li> Spaces around operators </li>
  <li> Brace Positions </li>
  ...
</ul>

با داشتن یک فرمت consistent، کد ما خوانایی و maintainability بیشتری پیدا می‌کند. برای مثال* به ما کمک می‌کند تا از visual noise و distraction های حاصل از indentation یا bracelet یا موارد این‌چنینی فاصله بگیریم. همچنین با ادغام این پلاگین با maven، مطمئن می‌شویم که با develop های بیشتر از سمت افراد مختلف و با evolve پروژه‌مان، به مشکلات این‌چنینی بر نمی‌خوریم و این پلاگین این synchronization ها را برایمان انجام می‌دهد. (در واقع می‌توانیم آنرا بخشی از پایپلاین CI/CD خود بدانیم که قبل از merge شدن کد آنرا به این‌گونه refactor می‌کند). ارتباط آن با refactoring در این است که با عملیات‌هایی که انجام می‌دهد، "Readability" کد را بالا می‌برد، "Maintainability" را بهبود می‌بخشد و در نهایت، باعث ساده‌تر شدن "Team Collaboration" می‌شود (هر کس می‌تواند تنطیمات شخصی خود را داشته باشد و این تاثیری در آنچه که روی production مرج می‌شود ندارد). درواقع، این پلاگین انواع ساده‌ای از refactoring را بصورت automated انجام می‌دهد و اگر مشکلی بابت آن باشد، کد مدنظر merge نمی‌شود و conflict ها اعلام می‌شوند تا برطرف شوند. پس همیشه مطمئن هستیم که کدی که روی production می رود، فارغ از تنظیمات شخصی IDE و برنامه‌نویس آن، با باقی codebase کاملا سینک است و از این منظرهای ساده (ولی مهم) refactor شده است. 
