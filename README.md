<p align="center">
    <img src="https://i.imgur.com/0dWpGYG.png" alt="Paradigm" width="80px" height="80px">
</p>
<h1 align="center"> Paradigm </h1>
<h3 align="center"> Open-Source Old School RuneScape Private-Server </h3>
<h5 align="center"> Revision: 204 (Update: 04/22/2022)</h5>

<!-- TABLE OF CONTENTS -->
<h2 id="table-of-contents"> :book: Table of Contents</h2>

<details open="open">
<summary>Table of Contents</summary>
<ol>
    <li><a href="#about"> ➤ About The Project</a></li>
    <li><a href="#getting-started"> ➤ Getting Started</a></li>
    <li><a href="#credits"> ➤ Credits</a></li>
</ol>
</details>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- ABOUT THE PROJECT -->
<h2 id="about"> :pencil: About The Project</h2>

<p align="justify">
Paradigm is an Open-Source Old School RuneScape private server designed to be both modular
and easy to update to previous and future versions of the game. It's also designed to be easy to use
and setup for novice users.

The project was started mostly for fun and learning aspects but also to provide a reasonable base framework for other
projects in the RSPS scene which want to use a modern framework.
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- GETTING STARTED -->
<h2 id="getting-started"> :book: Getting Started</h2>

- <p>First, clone the project to your system from this GitHub page.</p>

<pre><code>$ git clone https://github.com/paradigm-rsps/paradigm</code></pre>

- <p>Open the project directory inside of IntelliJ IDEA Community IDE. You can download this IDE for free from: <a href="https://www.jetbrains.com/idea/download/#section=windows">JetBrains IntelliJ IDEA Download</a></p>

- <p>Once the project is done initially building, Open the <b>Gradle</b> menu on the <u>Right-Hand</u> side.</p>

- <p>Open up the Gradle sub folder: <b>paradigm</b> by expanding the following: <b>paradigm > Tasks > paradigm</b></p>

- <p>Run the gradle task <b>"setup server"</b></p>

- <p>If you plan to use the OSRS NXT Steam client for this private server, run the gradle task <b>"patch nxt-client"</b></p>

- <p>You can now start the server with gradle task <b>"run server"</b></p>

Run the following task to start the client:<br>
Java client: <b>"run java-client"</b><br>
NXT Client: <b>"run nxt-client"</b>