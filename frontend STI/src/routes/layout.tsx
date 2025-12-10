import { Outlet } from 'react-router-dom'
import Navbar from '../components/NavBar'

export default function layout() {
  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <main className="flex-1 pt-16 px-4">
        <Outlet />
      </main>
    </div>
  )
}
