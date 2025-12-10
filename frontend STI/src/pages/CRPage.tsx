import { useEffect, useState } from 'react'
import { api } from '../services/api'
import Navbar from '../components/NavBar'

interface CRProps {
  id: string
  matriculaAluno: string
  nota: string
}

export default function CRPage() {
  const [crs, setCR] = useState<CRProps[]>([])

  useEffect(() => {
    loadcrs()
  }, [])

  async function loadcrs() {
    try {
      const response = await api.get("/crs")
      setCR(response.data)
    } catch (error) {
      console.error("Erro ao carregar crs:", error)
    }
  }

  async function baixarCSV() {
    try {
      const response = await api.get("/csv/crs", { responseType: 'blob' })
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      link.setAttribute('download', 'crs.csv')
      document.body.appendChild(link)
      link.click()
      link.remove()
    } catch (error) {
      console.error("Erro ao baixar CSV:", error)
    }
  }

  return (
    <div className="w-full min-h-screen bg-gray-900 flex flex-col">
      <Navbar />

      <main className="mt-24 my-10 w-full md:max-w-2xl mx-auto px-4">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-4xl font-medium text-white">CRS</h1>
          <button
            onClick={baixarCSV}
            className="p-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Baixar CSV
          </button>
        </div>

        <section className="flex flex-col gap-4">
          {crs.map(cr => (
            <div
              key={cr.id}
              className="w-full bg-white rounded p-2 relative hover:scale-105 duration-200"
            >
              <p><span className="font-medium">Matrícula do Aluno:</span> {cr.matriculaAluno}</p>
              <p><span className="font-medium">CR:</span> {cr.nota}</p>
              <p><span className="font-medium">Id:</span> {cr.id}</p>
            </div>
          ))}
        </section>
      </main>
    </div>
  )
}
