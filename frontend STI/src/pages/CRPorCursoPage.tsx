import { useEffect, useState } from 'react'
import { api } from '../services/api'
import Navbar from '../components/NavBar'

interface CRPorCurso {
  codigoCurso: string
  mediaCR: string
}

export default function CRPorCursoPage() {
  const [crs, setCrs] = useState<CRPorCurso[]>([])

  useEffect(() => {
    loadCRPorCurso()
  }, [])

  async function loadCRPorCurso() {
    try {
      const response = await api.get("/crs/media-por-curso")
      const data: CRPorCurso[] = response.data.map((item: any) => ({
        codigoCurso: item[0],
        mediaCR: item[1]
      }))
      setCrs(data)
    } catch (error) {
      console.error("Erro ao carregar CR por curso:", error)
    }
  }

  async function baixarCSV() {
    try {
      const response = await api.get("/csv/media-por-curso", { responseType: 'blob' })
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      link.setAttribute('download', 'media_cr_por_curso.csv')
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
          <h1 className="text-4xl font-medium text-white">CR por Curso</h1>
          <button
            onClick={baixarCSV}
            className="p-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Baixar CSV
          </button>
        </div>

        <section className="flex flex-col gap-3">
          {crs.map((curso, index) => (
            <div
              key={index}
              className="w-full bg-white rounded p-2 relative hover:scale-105 duration-200"
            >
              <p><span className="font-medium">Curso:</span> {curso.codigoCurso}</p>
              <p><span className="font-medium">CR Médio:</span> {curso.mediaCR}</p>
            </div>
          ))}
        </section>
      </main>
    </div>
  )
}
