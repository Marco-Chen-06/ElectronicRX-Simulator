<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/github_username/repo_name">
    <img src="https://www.dropbox.com/scl/fi/7pf5t7wpjxdq0ak53wg7b/ElectronicRXLogo.png?rlkey=ru6w96o5wn9qdq7gtbonyp9a6&raw=1" alt="Logo" width="500" height="300">
  </a>

<h1 align="center">ElectronicRX Simulator</h3>

  <p align="center">
    An educational pharmaceutical simulator for all ages!
    <br />

  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#getting-a-prescription">Getting a Prescription</a></li>
        <li><a href="#filling-out-a-prescription">Filling Out a Prescription</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#installation">Installation</a>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is an Electronic Prescription Software created using JavaFX for the Steel City Codes 2023 Spring Hackathon. The program provides a simplified medium for the user to experience filling prescriptions and researching medications. The following image demonstrates the general look of the software upon running the program.

[![Product Name Screen Shot][product-screenshot1]](https://example.com)

### Getting a Prescription

In order to obtain a prescription, you click one of the two buttons within the RXs section on the bottom right: Get Complete RX, or Get Incomplete RX. Each of these buttons creates a new Prescription object with randomized attributes and adds it to the dashboard. In the screenshot below, a complete and incomplete prescription was added to the dashboard.

[![Product Name Screen Shot][product-screenshot2]](https://example.com)


Upon clicking on a prescription in the dashboard, you can enter the editing screen. When editing an incomplete RX, you will notice that some information is missing. The missing information is limited to Drug Name, NDC#, Ingredient Cost, Copay, Amt Due, and SIG. In the screenshot below, Brandon Kennedy's prescription is opened. We can see that the NDC# and SIG are incorrect.

[![Product Name Screen Shot][product-screenshot3]](https://example.com)


When attempting to click the "Save" button, we are prompted with an alert of what the prescription is missing. (The SIG isn't mentioned because SIG represents the how a pharmacist or doctor instructs the drug to be taken. Although it is good practice to have a reasonable SIG, it will be optional within the scope of this program)

[![Product Name Screen Shot][product-screenshot4]](https://example.com)

### Filling Out a Prescription
When filling out an incomplete prescription, you may be missing essential information, such as the NDC# or Drug Name. To obtain this information, you can navigate to the Drug Index by pressing the bottom left button in the menu. Here, you can search through different medication based on their names and NDC#, and you can find any essential information necessary to correctly fill out a prescription. 

Because Brandon Kennedy's prescription had an NDC# of 0071-0401-24, we can navigate to the Drug that corresponds to the respective NDC number in the Drug Index.

[![Product Name Screen Shot][product-screenshot5]](https://example.com)


From the Drug Index, we found the drug corresponding to the NDC# was Gabapentin Extended-Release, and the SIG instructs the patient to take 3 pills daily. We can put this information into our Brandon Kennedy's prescription and complete the prescription!

[![Product Name Screen Shot][product-screenshot6]](https://example.com)
### Have fun!

It may be difficult at first to locate the information necessary to fill out the incomplete prescriptions. However, once you get used to the interface of the software and understand how all the information works together, you can start filling perfect prescriptions with little effort! After that, you can be your own pharmacist! Play with the sorting features, discover new medicine, or discover some interesting randomly generated names! Good luck!

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* Java 
* JavaFX

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->

### Installation

1. Download all files in the Github Repository (Or clone the repo, whichever you prefer!)
2. Open it in any IDE with JavaFX support! (IntelliJ IDEA, VSCode, it's your choice!)
3. Run the program in ElectronicRX.java
4. Enjoy!

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Marco Chen - marcochen.work@gmail.com

Project Link: https://github.com/Marco-Chen-06/ElectronicRX-Simulator

<p align="right">(<a href="#readme-top">back to top</a>)</p>




<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[logo1]: https://www.dropbox.com/scl/fi/7pf5t7wpjxdq0ak53wg7b/ElectronicRXLogo.png?rlkey=ru6w96o5wn9qdq7gtbonyp9a6&raw=1
[product-screenshot1]: https://www.dropbox.com/scl/fi/lk3es07871l5lqid46iqo/BlankRX.png?rlkey=ourufliekaw9wkxlthx5hauad&raw=1
[product-screenshot2]: https://www.dropbox.com/scl/fi/90l6j4w3p6qqxzh1p53d5/CompleteIncompleteRX.png?rlkey=79wct88m7rwlyfrip5bgp8lqr&raw=1
[product-screenshot3]: https://www.dropbox.com/scl/fi/59qu8hoethhzgcv4yz6e3/IncompleteRXExample.png?rlkey=dqw450reslrsbdixbqthogzgl&raw=1
[product-screenshot4]: https://www.dropbox.com/scl/fi/ltbzeh8ipxzcyaj4tmnao/DrugNameIncorrect.png?rlkey=55y0i4f5cpqfm8duvtck5mdvk&raw=1
[product-screenshot5]: https://www.dropbox.com/scl/fi/mbhdnh5r02p3qmm984zsg/DrugIndex.png?rlkey=2lnc90rnaomjbmttu438unt5j&raw=1
[product-screenshot6]: https://www.dropbox.com/scl/fi/8v56y5uh955k6yk5tabaw/PerfectPrescription.png?rlkey=a3947cxws0im1ypkygug40x8g&raw=1
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
