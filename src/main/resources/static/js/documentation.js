// Navigation functionality
document.addEventListener("DOMContentLoaded", () => {
  // Elements
  const mobileMenuBtn = document.getElementById("mobileMenuBtn")
  const sidebar = document.getElementById("sidebar")
  const mobileOverlay = document.getElementById("mobileOverlay")
  const navItems = document.querySelectorAll(".nav-item")
  const contentSections = document.querySelectorAll(".content-section")
  const tabBtns = document.querySelectorAll(".tab-btn")
  const tabContents = document.querySelectorAll(".tab-content")
  const copyBtns = document.querySelectorAll(".copy-btn")

  // Mobile menu toggle
  mobileMenuBtn.addEventListener("click", () => {
    sidebar.classList.toggle("open")
    mobileOverlay.classList.toggle("active")
  })

  // Close mobile menu when clicking overlay
  mobileOverlay.addEventListener("click", () => {
    sidebar.classList.remove("open")
    mobileOverlay.classList.remove("active")
  })

  // Navigation between sections
  navItems.forEach((item) => {
    item.addEventListener("click", function () {
      const sectionId = this.getAttribute("data-section")

      // Update active nav item
      navItems.forEach((nav) => nav.classList.remove("active"))
      this.classList.add("active")

      // Show corresponding section
      contentSections.forEach((section) => {
        section.classList.remove("active")
        if (section.id === sectionId) {
          section.classList.add("active")
        }
      })

      // Close mobile menu
      sidebar.classList.remove("open")
      mobileOverlay.classList.remove("active")

      // Scroll to top
      window.scrollTo({ top: 0, behavior: "smooth" })
    })
  })

  // Tab functionality
  tabBtns.forEach((btn) => {
    btn.addEventListener("click", function () {
      const tabId = this.getAttribute("data-tab")
      const parentTabs = this.closest(".tabs")
      const tabBtnsInGroup = parentTabs.querySelectorAll(".tab-btn")
      const tabContentsInGroup = parentTabs.querySelectorAll(".tab-content")

      // Update active tab button
      tabBtnsInGroup.forEach((tab) => tab.classList.remove("active"))
      this.classList.add("active")

      // Show corresponding tab content
      tabContentsInGroup.forEach((content) => {
        content.classList.remove("active")
        if (content.id === tabId) {
          content.classList.add("active")
        }
      })
    })
  })

  // Copy to clipboard functionality
  copyBtns.forEach((btn) => {
    btn.addEventListener("click", function () {
      const code = this.getAttribute("data-code")
      const copyIcon = this.querySelector(".copy-icon")

      // Copy to clipboard
      navigator.clipboard
        .writeText(code)
        .then(() => {
          // Visual feedback
          const originalIcon = copyIcon.textContent
          copyIcon.textContent = "✓"
          this.classList.add("copied")

          // Reset after 2 seconds
          setTimeout(() => {
            copyIcon.textContent = originalIcon
            this.classList.remove("copied")
          }, 2000)
        })
        .catch((err) => {
          console.error("Error copying to clipboard:", err)
          // Fallback for older browsers
          const textArea = document.createElement("textarea")
          textArea.value = code
          document.body.appendChild(textArea)
          textArea.select()
          document.execCommand("copy")
          document.body.removeChild(textArea)

          // Visual feedback
          const originalIcon = copyIcon.textContent
          copyIcon.textContent = "✓"
          this.classList.add("copied")

          setTimeout(() => {
            copyIcon.textContent = originalIcon
            this.classList.remove("copied")
          }, 2000)
        })
    })
  })

  // Close mobile menu on window resize
  window.addEventListener("resize", () => {
    if (window.innerWidth > 768) {
      sidebar.classList.remove("open")
      mobileOverlay.classList.remove("active")
    }
  })

  // Smooth scrolling for anchor links
  document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
    anchor.addEventListener("click", function (e) {
      e.preventDefault()
      const target = document.querySelector(this.getAttribute("href"))
      if (target) {
        target.scrollIntoView({
          behavior: "smooth",
          block: "start",
        })
      }
    })
  })
})
