import Navbar from '../components/NavBar'

export default function HomePage() {
  return (
    <div className="w-full min-h-screen bg-gray-900 text-white px-4">
      <Navbar />
      <div className="pt-20 flex flex-col items-center justify-center">
        <h1 className="text-5xl font-bold mb-4 text-center">Desafio 3</h1>
        <p className="text-lg mb-8 text-center max-w-xl font-serif">Caio da Conceição Lopes</p>
      </div>
    </div>
  )
}
