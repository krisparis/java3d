# Javadoc Guidelines <a id="#title"></a>#

- [Format of a Javadoc comment](#format_section)
 - [General structure](#generalstructure_subsection)
 - [Use of space characters](#usespace_subsection)
 - [Use <code\\> for keywords and names](#usecode_subsection)
 - [Use of tags](#usetags_subsection) 
- [Writting descriptions](#description_section)
 - [Main recommandations](#mainrecommandations_section)
 - [Class Description](#classdescription_section) 
 - [Method Description](#methoddescription_section)


## Format of a Javadoc comment <a id="#format_section"></a> ##

### General structure <a id="#generalstructure_subsection"></a> ###

Below is the general structure a Javadoc comment should follow.

> /**<br/>
> First paragraph.<br/>
> <p\>Additional paragraph 1.</p\> <br/>
> <p\>Additional paragraph 2.</p\> <br/>
> <i><List description line\>:</i> <br/>
> <ul\> <br/>
> &nbsp;&nbsp;<li\>List item 1</li\> <br/>
> &nbsp;&nbsp;<li\>List item 2</li\> <br/>
> <ul\> <br/>
> <BLANK LINE\> <br/>
> @param paramName paramDescrption<br/>
> */

### Use of space characters <a id="#usespace_subsection"></a> ###

- **Never use tabulation characters!**
- Never leave blank lines at the beginning or end of the comment.
- Leave one blank line between paragraphs, lists, and between the last paragraph in the description and the first Javadoc tag (`@author` for instance).
- Use two space characters to indent list items. (**<li\> </li\>**)

### Use <code\> for keywords and names <a id="#usecode_subsection"></a> ###

Keywords and names are offset by <code>...</code> when mentioned in a description. This includes:

- Java keywords
- package names
- class names
- method names
- interface names
- field names
- argument names
- code examples

### Use of tags <a id="#usetags_subsection"></a> ###

#### Order of tags ####

- @author (classes and interfaces only, required)
- @version (classes and interfaces only, required. See footnote 1)
- @param (methods and constructors only)
- @return (methods only)
- @exception (@throws is a synonym added in Javadoc 1.2)
- @see
- @since
- @serial (or @serialField or @serialData)
- @deprecated (see How and When To Deprecate APIs)

#### Ordering Multiple Tags ####
We employ the following conventions when a tag appears more than once in a documentation comment. If desired, groups of tags, such as multiple @see tags, can be separated from the other tags by a blank line with a single asterisk.

Multiple **@author** tags should be listed in chronological order, with the creator of the class listed at the top.

Multiple **@param** tags should be listed in argument-declaration order. This makes it easier to visually match the list to the declaration.

Multiple **@throws** tags (also known as @exception) should be listed alphabetically by the exception names.  

Multiple **@see** tags should be ordered as follows, which is roughly the same order as their arguments are searched for by javadoc, basically from nearest to farthest access, from least-qualified to fully-qualified, The following list shows this progression. Notice the methods and constructors are in "telescoping" order, which means the "no arg" form first, then the "1 arg" form, then the "2 arg" form, and so forth. Where a second sorting key is needed, they could be listed either alphabetically or grouped logically.

@see #field <br/>
@see #Constructor(Type, Type...) <br/>
@see #Constructor(Type id, Type id...) <br/>
@see #method(Type, Type,...) <br/>
@see #method(Type id, Type, id...) <br/>
@see Class <br/>
@see Class#field <br/>
@see Class#Constructor(Type, Type...) <br/>
@see Class#Constructor(Type id, Type id) <br/>
@see Class#method(Type, Type,...) <br/>
@see Class#method(Type id, Type id,...) <br/>
@see package.Class <br/>
@see package.Class#field <br/>
@see package.Class#Constructor(Type, Type...) <br/>
@see package.Class#Constructor(Type id, Type id) <br/>
@see package.Class#method(Type, Type,...) <br/>
@see package.Class#method(Type id, Type, id) <br/>
@see package

#### Required tags ####

An **@param** tag is "required" (by convention) for every parameter, even when the description is obvious. The **@return** tag is required for every method that returns something other than void, even if it is redundant with the method description. (Whenever possible, find something non-redundant (ideally, more specific) to use for the tag comment.)

These principles expedite automated searches and automated processing. Frequently, too, the effort to avoid redundancy pays off in extra clarity.

## Writting descriptions <a id="#description_section"></a> ##

[Back to top](#title)

### Main recommandations <a id="#mainrecommandations_section"></a> ###

#### Use 3rd person (descriptive) not 2nd person (prescriptive). ####
The description is in 3rd person declarative rather than 2nd person imperative.

*Gets the label. (preferred)*<br/>
*Get the label. (avoid)*

#### Avoid Latin ####

Use "also known as" instead of "aka", use "that is" or "to be specific" instead of "i.e.", use "for example" instead of "e.g.", and use "in other words" or "namely" instead of "viz.

#### Use "this" instead of "the" when referring to an object created from the current class. ####
For example, the description of the getToolkit method should read as follows:

*Gets the toolkit for this component. (preferred)*<br/>
*Gets the toolkit for the component. (avoid)*

#### Class/interface/field descriptions can omit the subject and simply state the object ####
These API often describe things rather than actions or behaviors:

*A button label. (preferred)* <br/>
*This field is a button label. (avoid)*

#### Referencing classes with @see and @link ####

- `@see` and `@link` should not include the full path unless the class is in another package. If the class is in another package, then the full path to the class (e.g.{`@link` com.company.kernel.util.Constants}) should be specified.

#### Referencing classes constants ####
- With regards to class constants, be sure to reference their class either by including the full path of the constant (e.g.{@link com.company.kernel.util.Constants#TYPE_ASSET}) or wrapping the constant(s) in `<code></code>` tags followed by a reference to the class with its full path. For example, `<code>TYPE_ASSET</code>` and `<code>TYPE_CREATOR</code>` defined in {@link com.company.social.model.SocialActivityCounterConstants}.

#### Use in-line links economically ####

Use the {**@link**} tag **only for the first occurrence** of each API name in the doc comment (don't bother repeating a link).

Do not use the {**@link**} tag to link to API in the java.lang package (such as String), or other API you feel would be well-known.

#### Be clear when using the term "field". ####
Be aware that the word "field" has two meanings: <br/>

- static field, which is another term for "class variable"
- text field, as in the TextField class. Note that this kind of field might be restricted to holding dates, numbers or any text. Alternate names might be "date field" or "number field", as appropriate.

#### Omit parentheses for the general form of methods and constructors ####

When referring to a method or constructor that has multiple forms, omit parentheses if referring to all forms of the method.<br/>
For example, ArrayList has two add methods: `add(Object)` and `add(int, Object)`:

*The `add` method enables one developer to insert items. (preferred)* <br/>
*The `add()` method enables one developer to insert items. (avoid when you mean "all forms" of the add method)* <br/>
*The `add(int, Object)` method adds an item at a specified position in this arraylist. (OK)* 

#### Implementation-Independence ####

- As much as possible, write doc comments as an implementation-independent API specification.
- Define clearly what is required and what is allowed to vary across platforms/implementations.
- Ideally, make it complete enough for conforming implementors. Realistically, include enough description so that someone reading the source code can write a substantial suite of conformance tests. Basically, the spec should be complete, including boundary conditions, parameter ranges and corner cases.
- Where appropriate, mention what the specification leaves unspecified or allows to vary among implementations.
- If you must document implementation-specific behavior, please document it **in a separate paragraph**  with a lead-in phrase that makes it clear it is implementation-specific.
-  **If the implementation varies according to platform**, then specify **"On <platform\>"** at the start of the paragraph.
-  In other cases that might **vary with implementations on a platform** you might use the lead-in phrase **"Implementation-Specific:"**

 Here is an example of an implementation-dependent part of the specification for `java.lang.Runtime`:

*On Windows systems, the path search behavior of the loadLibrary method is identical to that of the Windows API's LoadLibrary procedure*


### Class description <a id="#classdescription_section"></a> ###

[Back to top](#title)

Whenever possible, **start** the initial description of a class **with a verb**.

#### Model class ####

Use the following pattern:

>****Represents** *<an entity\>* **, providing** *<summary of provided data\>*

Here is an example from `User.java`:<br/>
*Represents an user, providing access to the user's contact
information, groups, organizations, teams, user groups, roles, locale,
timezone, and more.*

#### Model interface and implementation ####

Use the following pattern for model interfaces:

>**Provides the model interface for** *<an entity\>*. **Represents** *<an entity\>* **, providing** *<summary of provided data\>*

Use the following pattern for model interface implementations:

>**Provides the model implementation for** *<an entity\>*. **Represents** *<an entity\>* **, providing** *<summary of provided data\>*

Below is an example from `Model.java `:<br/>
*Provides the model interface for the User service. Represents an User, providing access to the user's contact
information, groups, organizations, teams, user groups, roles, locale,
timezone, and more.*

#### Service implementations ####

Use the following pattern:

>**Provides the (local/remote) service for** *<summary of methods using actions ending in “ing”\>*.

Below is an example from `DLAppLocalServiceImpl` :<br/>
*Provides the local service for accessing, adding, deleting, moving,
subscription handling of, trash handling of, and updating document library
file entries, file ranks, and folders. All portlets should interact with
the document library through this class or through DLAppService, rather
than through the individual document library service classes.*

#### Utility class ####

Use the following pattern:

>**Provides utility methods for** *<summary of methods using actions ending in “ing”\>*

- Action verbs shoud be listed in **alphabetical order**
- Verbs can be derivated from the class' method names.

Below is an example for `Localization` interface:<br/>
*Provides utility methods for updating localizations from JSON, maps and portlet requests.*

### Method description <a id="#methoddescription_section"></a> ###

- Whenever possible, **start** a method description **with a verb**.
- When referring to parameters, use "the" instead of "a". Example: "Returns the localized preferences value for the key."
- When mentionning the name of a method argument use "the *<paramName\>* argument".

#### Initial method descriptions ####

##### Constructor #####

- constructor(value): **Constructs a new** *<entity\>*... with the value.

##### Methods returning values #####

[Back to top](#title)

###### General patterns ######

- getSomething(): **Returns the** *something* of this thing.
- getSomethings(): **Returns all the** *something**s*** of this thing. (Note, do not refer to collection type; instead, refer to the something in plural form.)
- getSomething(<field1\>): **Returns the** *something* of this thing **with the** <field1\>.
- getSomething(<field1\>,...,<fieldN\>): **Returns the** *something* of this thing **matching the** <field1\>, ... and <fieldN\>.
- isSomething(): **Returns <code\>true</code\> if** this thing is *something*.

In case of methods returning:

- a **count:** Use the following pattern: **Returns the number of** *<something>* of this thing
- an **ordered collection;** add a phrase following the pattern "**ordered by** *<order criteria\>*"

Examples: 

> Returns the name of this user. <br/>
> Returns all the adresses of this user. <br/>
> Returns the number of organizations this user belongs to and matching the type, region, and country <br/>
> Returns <code\>true</code\> if this user is registered. <br/>


##### Method updating entities #####

- setSomething(value): **Sets the** *something* of this thing.
- setSomething(boolean): **Sets whether** this thing is *something*.
- deleteSomething(): **Deletes the** *something*.

#### @param tag ####

<table>
<tr>
<td> Param case </td>
<td> Param name convention </td>
<td> @param Description </td>
</tr>

<tr>
<td> An entity's ID </td>
<td> entity<b>ID</b> </td>
<td> <b>the primary key of the</b> entity </td>
</tr>

<tr>
<td> An entity's attribute or field </td>
<td> the entity's field name </td>
<td> <b>the</b> entity<b>'s</b> <i>attribute</i></td>
</tr>

<tr>
<td> An involved entity (possibly the method's subject) </td>
<td> <i>classname (lowercase)</i> </td>
<td> <b>the</b> <i>datatype</i> to be ... (refer to the entity in layman's terms in lower case. Do not refer to the uppercase classname)</td>
</tr>

<tr>
<td> A int, float or double parameter </td>
<td>  <i>meaningfullName (lowercase)</i></td>
<td> <b>the</b> ... </td>
</tr>

<tr>
<td> A boolean parameter </td>
<td> past participe verb </td>
<td> <b>whether</b> to do something <br/>or <i>some condition</i> is true</td>
</tr>
</table>

Below are some examples:

>  @param companyId&nbsp;&nbsp;the primary key of the user's company <br/>
>  @param name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the user's name <br/>
>  @param ch&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the character to be tested <br/>
>  @param observer&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the image observer to be notified <br/>
>  @param x&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;the x-coordinate, measured in pixels <b><- this is a float parameter</b> <br/>
>  @param  trusted&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;whether to bypass permission checks. <br/>
>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In third-party repositories, this parameter may be ignored.


#### @return tag ####

<table>
<tr>
<td> Method Type </td>
<td> @return Description </td>
</tr>

<tr>
<td> getSomething() </td>
<td> <b>the</b> <i>something</i> of this thing</td>
</tr>

<tr>
<td> getSomething(<i>field1</i>)</td>
<td> <b>the</b> <i>something</i> <b>with the</b> <i>field1</i> of this thing</td>
</tr>

<tr>
<td> getSomething(<i>field1</i>, ..., <i>fieldN</i>)</td>
<td> <b>the matching</b> <i>something</i> <b></td>
</tr>

<tr>
<td> getSomething<b>s</b>() </td>
<td> <b>all the</b> <i>somethings</i> of this thing</td>
</tr>

<tr>
<td> getSomething<b>s</b>(<i>field1</i>, ..., <i>fieldN</i>)</td>
<td> <b>all the matching</b> <i>somethings</i> <b></td>
</tr>

<tr>
<td>changeSomething(thing)</td>
<td> the changed thing, or <code>null</code> if the change failed </td>
</tr>

<tr>
<td> getNbSomething() </td>
<td> <b>the number of</b> <i>something</i></td>
</tr>

<tr>
<td> isSomething() </td>
<td> <b><code>true</code> if </b><i>something</i>; <b><code>false</code> otherwise</b>
</td>
</tr>
</table>

Examples: 

> the name of this user. <br/>
> all the adresses of this user. <br/>
> the number of organizations this user belongs to and matching the type, region, and country <br/>
> <code\>true</code\> if this user is registered, <code\>false</code\> otherwise. <br/>

#### Try to avoid simply restating the name of the method in the description ####

In some cases, such as with getters/setters, this is fine, but if it is not immediately obvious what a method does, it needs more explanation.

**Avoid** - The description below says nothing beyond what you know from reading the method name. The words "set", "tool", "tip", and "text" are simply repeated in a sentence.

> /**<br/>
>  * Sets the tool tip text.<br/>
>  *<br/>
>  * @param text  the text of the tool tip <br/>
>  */ <br/>
>  public void setToolTipText(String text) {

**Preferred** - This description more completely defines what a tool tip is, in the larger context of registering and being displayed in response to the cursor.

> /** <br/>
>  * Registers the text to display in a tool tip.   The text <br/> 
>  * displays when the cursor lingers over the component. <br/>
>  * <br/>
>  * @param text  the string to display.  If the text is null, <br/> 
>  * the tool tip is turned off for this component. <br/>
>  */ <br/>
> public void setToolTipText(String text) {


#### Distinguish between constructors ####

Here are two examples:
>    /** <br/>
>     * Constructs a new foo. <br/>
>     */ <br/>
>    Foo() { <br/>
>      ... <br/>

 and   
>    /** <br/>
>     * Constructs a new foo with the number of objects to create. <br/>
>     */ <br/>
>    Foo(int n) { <br/>


#### Distinguish between overloaded methods ####

- **UNIQUELY** describe each method **in the first sentence** of the method description!

- Remember, only the first sentence of the method description shows in the method summary. The remaining sentences (including sentences of the same paragraph following the first sentence) are available in the full length method description.

> /** <br/>
>  * Returns all immediate subfolders of the parent folder. <br/>
>  * ... <br/>
>  */ <br/>
> public List<FoldergetFolders(long repositoryId, long parentFolderId) <br/>

> /**<br/>
>  * Returns all immediate subfolders of the parent folder, optionally <br/>
>  * including mount folders for third-party repositories. <br/>
>  * .... <br/>
>  */ <br/>
> public List<FoldergetFolders(long repositoryId, long parentFolderId, boolean includeMountFolders) <br/>

